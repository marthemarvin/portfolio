package com.marwan.portfolio.dto;

import com.marwan.portfolio.entity.About;
import com.marwan.portfolio.entity.AdditionalInfo;

import java.util.List;

public record AboutResponse(
		// normal personal info about me
		About about,

		// additional info for example helping al shay3 and boutiqaat
		List<AdditionalInfo> additionalInfo) {
}
