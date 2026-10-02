# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

Spring Boot 4.1 (Java 21) REST backend for a personal portfolio site. The frontend is a separate Angular 17 (SSR) project at `~/Desktop/portfolio-ui` (dev server on `http://localhost:4200`, production at `https://marwankw.com`). The production API is `https://api.marwankw.com`.

Note: this repo's directory name has a trailing space (`~/Desktop/portfolio `) — quote paths in shell commands.

## Commands

```bash
./mvnw spring-boot:run                 # run locally (profile "local" is the default)
./mvnw clean package                   # build jar into target/
./mvnw test                            # run all tests
./mvnw test -Dtest=ClassName#method    # run a single test
docker build -t portfolio .            # Docker build (skips tests)
```

The shell's default `java` is 8; run Maven with `JAVA_HOME=/opt/homebrew/opt/openjdk@21`. The `local` profile expects PostgreSQL at `localhost:5432/portfolio`. `PortfolioApplicationTests` is a `@SpringBootTest`, so tests also need that database running.

Frontend (in `portfolio-ui`): `npm start` (ng serve), `npm run build`, `npm test` (Karma/Jasmine).

## Project structure

Code is organised by layer under `com.marwan.portfolio`. New classes go in the matching package — never flat in the root:

| Package | Contents |
|---|---|
| `controller` | REST controllers |
| `service` | Business logic |
| `repository` | Spring Data JPA repositories |
| `entity` | JPA entities |
| `dto` | Request bodies (Java records, e.g. `CourseRequest`, `LoginRequest`) |
| `config` | `SecurityConfig`, `CorsConfig` |
| `exception` | `ApiException` |
| `specification` | Reusable JPA filters (`Specs`) |

`PortfolioApplication` stays at the root so component scanning covers all subpackages. Lombok is used (`@Data`, `@RequiredArgsConstructor`).

## Architecture

- **Profiles/config**: `application.yaml` holds shared settings (`ddl-auto: update`, so JPA entities drive the schema — no migrations). `application-local.yaml` has hardcoded dev credentials; `application-prod.yaml` reads everything from env vars: `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `ADMIN_EMAIL`, `ADMIN_PASSWORD`, `JWT_SECRET`.
- **Auth**: single-admin model, no user table. `POST /api/auth/login` (`AuthController` → `AuthService`) compares credentials against `admin.email`/`admin.password` config and returns a raw HS256 JWT string (1h expiry) signed with `jwt.secret` (must be ≥32 chars). `SecurityConfig` wires the same secret into both the `JwtEncoder` and the OAuth2 resource-server `JwtDecoder`, so clients send it as `Authorization: Bearer <token>`. Any validly signed, unexpired token is accepted — there are no roles/scopes.
- **Access rules** (`SecurityConfig`): everything under `/api/admin/**` requires a valid JWT (any method); outside that, `GET` and login are public and other methods require a JWT. CSRF is disabled.
- **CORS** is configured in `CorsConfig` (allowed origins: `https://marwankw.com`, `http://localhost:4200`). Add origins there, not in `SecurityConfig`.

## Conventions for new endpoints

- **Layering**: controller → service → repository. Controllers contain no logic — they only receive the request and delegate to one service method. All logic lives in the service.
- **Public vs admin**: each resource has a public controller (`/api/<resource>`, e.g. `CourseController`) and an admin controller (`/api/admin/<resource>`, e.g. `AdminCourseController`) for CRUD. Public endpoints only expose active records (`isActive = true`) — including get-by-id, which returns 404 for inactive records. Public lists can be ordered with a derived query (e.g. `findByIsActiveTrueOrderByStartDateDesc` for experiences, newest first).
- **Single-record resources** (e.g. `About`): no id in the URL, no list/delete, no `isActive`. The admin controller exposes `GET` and `PUT /api/admin/<resource>`, where `PUT` creates the record the first time and updates it after that; the repository reads it with `findFirstByOrderByIdAsc()`.
- **Request/response**: request bodies are records in `dto`, mapped onto the entity in the service (so clients can't set `id` or timestamps). Entities are returned directly as responses. `PUT` replaces all fields.
- **Errors**: throw `ApiException(HttpStatus, message)` from the service. The message reaches the client because `application.yaml` sets `spring.web.error.include-message: always` (Spring Boot 4 name — the old `server.error.include-message` no longer affects the JSON error body). Use explicit `Optional` checks (defensive style), not `orElseThrow`:
  ```java
  Optional<Course> course = courseRepository.findById(id);

  if (course.isEmpty()) {
  	throw new ApiException(HttpStatus.NOT_FOUND, "Course not found");
  }

  return course.get();
  ```
- **Status codes**: controllers don't use `@ResponseStatus` — create, update and delete all return the default 200.
- **Pagination**: "get all" admin endpoints take a `Pageable` and return `Page<T>` (`?page=0&size=10&sort=field,desc`; default size 20).
- **Entities**: explicit snake_case `@Column(name = ...)` on every field; `@CreationTimestamp`/`@UpdateTimestamp` for `created_at`/`updated_at`; an `is_active` (`Boolean isActive`) column to hide records from the public API. `ddl-auto: update` adds new columns but never alters existing ones (e.g. a column type change must be done manually in SQL).

## Reusable filters (`specification/Specs`)

`Specs` provides generic, null-safe JPA `Specification` builders that work for any entity. A filter whose value is `null` (or blank, for strings) returns `Specification.unrestricted()`, so optional request params can be passed straight in.

- `Specs.equal(field, value)` — exact match
- `Specs.like(field, value)` — case-insensitive "contains"

To filter a resource, the repository extends `JpaSpecificationExecutor<T>`, and the service combines filters with `Specification.allOf(...)`:

```java
courseRepository.findAll(Specification.allOf(
		Specs.like("title", title),
		Specs.equal("isActive", isActive)
), pageable);
```

Field names are the entity's Java field names (e.g. `isActive`), not column names. Add new generic filter types (date ranges, `in`, etc.) as static methods on `Specs` rather than writing entity-specific ones.

## Postman

Every endpoint that is added or changed must also be added/updated in Postman — collection **"portfolio"** in the "Marwan Hosam's Workspace" workspace (team "Marwan Hosam's Team"). Don't touch the other collections in that workspace.

- The collection has two top-level folders: `local` (`http://localhost:8080`) and `prod` (`https://api.marwankw.com`). Every request is added to **both**.
- Inside each, requests are grouped into one subfolder per resource (currently `Auth`, `Courses`, `About` and `Experiences`). A new resource gets its own subfolder in both `local` and `prod`.
- Requests that need a token get **"(Admin)"** at the end of the name (e.g. `Get All Courses (Admin)`) and use Bearer auth with `{{token}}`. Public requests have no suffix and no auth.
- Requests with a body include a `Content-Type: application/json` header and an example JSON body; paginated requests include `page`, `size` and `sort` query params.
