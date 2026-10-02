package com.marwan.portfolio.service.admin;

import com.marwan.portfolio.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtEncoder tokenGenerator;

	@Value("${admin.email}")
	private String adminEmail;

	@Value("${admin.password}")
	private String adminPassword;

	public String login(String email, String password) {

		// check if email is == to the env email and pass i set
		if (!adminEmail.equals(email) || !adminPassword.equals(password)) {
			throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
		}

		// builds the information
		Instant now = Instant.now();
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.subject(email)
				.issuedAt(now)
				.expiresAt(now.plus(1, ChronoUnit.HOURS))
				.build();

		// encodes the information into a token
		JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
		return tokenGenerator.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
	}

}
