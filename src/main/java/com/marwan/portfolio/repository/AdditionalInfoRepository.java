package com.marwan.portfolio.repository;

import com.marwan.portfolio.entity.AdditionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdditionalInfoRepository extends JpaRepository<AdditionalInfo, Long> {

	// in the order set by the reorder endpoint
	List<AdditionalInfo> findByIsActiveTrueOrderByDisplayOrderAsc();

	// admin list, hidden ones included
	List<AdditionalInfo> findAllByOrderByDisplayOrderAscIdAsc();

}
