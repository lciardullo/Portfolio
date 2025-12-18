package com.portfolio.repository;

import com.portfolio.model.SoldAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldAssetRepository extends JpaRepository<SoldAsset, Long> {
}

