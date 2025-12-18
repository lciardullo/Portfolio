package com.portfolio.repository;

import com.portfolio.model.PortfolioSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioSummaryRepository extends JpaRepository<PortfolioSummary, Long> {
}

