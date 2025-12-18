package com.portfolio.controller;

import com.portfolio.dto.*;
import com.portfolio.service.PortfolioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@CrossOrigin(origins = "http://localhost:3000")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/summary")
    public ResponseEntity<PortfolioSummaryDTO> getSummary() {
        PortfolioSummaryDTO summary = portfolioService.getSummary();
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/assets")
    public ResponseEntity<List<AssetDTO>> getAssets() {
        List<AssetDTO> assets = portfolioService.getAssets();
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/sold-assets")
    public ResponseEntity<List<SoldAssetDTO>> getSoldAssets() {
        List<SoldAssetDTO> soldAssets = portfolioService.getSoldAssets();
        return ResponseEntity.ok(soldAssets);
    }

    @GetMapping("/distribution")
    public ResponseEntity<List<PortfolioDistributionDTO>> getDistribution() {
        List<PortfolioDistributionDTO> distribution = portfolioService.getDistribution();
        return ResponseEntity.ok(distribution);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> depositMoney(@Valid @RequestBody MoneyTransactionDTO transaction) {
        portfolioService.depositMoney(transaction.getAmount());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Void> withdrawMoney(@Valid @RequestBody MoneyTransactionDTO transaction) {
        try {
            portfolioService.withdrawMoney(transaction.getAmount());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/update-prices")
    public ResponseEntity<PortfolioSummaryDTO> updatePrices() {
        portfolioService.updateAssetPrices();
        PortfolioSummaryDTO summary = portfolioService.getSummary();
        return ResponseEntity.ok(summary);
    }
}

