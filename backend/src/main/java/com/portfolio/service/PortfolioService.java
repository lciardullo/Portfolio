package com.portfolio.service;

import com.portfolio.dto.*;
import com.portfolio.model.*;
import com.portfolio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortfolioService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private SoldAssetRepository soldAssetRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PortfolioSummaryRepository portfolioSummaryRepository;

    @Autowired
    private StockPriceService stockPriceService;

    public PortfolioSummaryDTO getSummary() {
        PortfolioSummary summary = getOrCreateSummary();
        return convertToSummaryDTO(summary);
    }

    private PortfolioSummary getOrCreateSummary() {
        return portfolioSummaryRepository.findAll().stream()
                .findFirst()
                .orElseGet(() -> {
                    PortfolioSummary newSummary = new PortfolioSummary();
                    newSummary.setMoneyDeposited(0.0);
                    newSummary.setLiquidMoney(0.0);
                    newSummary.setMoneyInvested(0.0);
                    newSummary.setPerformance(0.0);
                    newSummary.setTotalValue(0.0);
                    newSummary.setPerformancePercent(0.0);
                    return portfolioSummaryRepository.save(newSummary);
                });
    }

    public List<AssetDTO> getAssets() {
        return assetRepository.findAll().stream()
                .map(this::convertToAssetDTO)
                .collect(Collectors.toList());
    }

    public List<SoldAssetDTO> getSoldAssets() {
        return soldAssetRepository.findAll().stream()
                .map(this::convertToSoldAssetDTO)
                .collect(Collectors.toList());
    }

    public List<PortfolioDistributionDTO> getDistribution() {
        List<Asset> assets = assetRepository.findAll();
        double totalValue = assets.stream()
                .mapToDouble(Asset::getTotalCurrent)
                .sum();

        return assets.stream()
                .map(asset -> {
                    PortfolioDistributionDTO dto = new PortfolioDistributionDTO();
                    dto.setSymbol(asset.getSymbol());
                    dto.setValue(asset.getTotalCurrent());
                    dto.setPercentage(totalValue > 0 ? (asset.getTotalCurrent() / totalValue) * 100 : 0);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void depositMoney(Double amount) {
        PortfolioSummary summary = getOrCreateSummary();

        summary.setMoneyDeposited(summary.getMoneyDeposited() + amount);
        summary.setLiquidMoney(summary.getLiquidMoney() + amount);
        portfolioSummaryRepository.save(summary);

        Transaction transaction = new Transaction();
        transaction.setType("DEPOSIT");
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setDescription("Depósito de dinero");
        transactionRepository.save(transaction);
    }

    @Transactional
    public void withdrawMoney(Double amount) {
        PortfolioSummary summary = getOrCreateSummary();

        if (summary.getLiquidMoney() < amount) {
            throw new IllegalArgumentException("No hay suficiente dinero líquido disponible");
        }

        summary.setLiquidMoney(summary.getLiquidMoney() - amount);
        portfolioSummaryRepository.save(summary);

        Transaction transaction = new Transaction();
        transaction.setType("WITHDRAW");
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setDescription("Retiro de dinero");
        transactionRepository.save(transaction);
    }

    /**
     * Actualiza los precios actuales de todos los activos
     */
    @Transactional
    public void updateAssetPrices() {
        List<Asset> assets = assetRepository.findAll();
        
        for (Asset asset : assets) {
            Double newPrice = stockPriceService.getCurrentPrice(asset.getSymbol());
            asset.setCurrentPrice(newPrice);
            asset.setTotalCurrent(newPrice * asset.getUnits());
            
            // Calcular performance
            Double performance = asset.getTotalCurrent() - asset.getTotalPurchase();
            asset.setPerformance(performance);
            
            if (asset.getTotalPurchase() > 0) {
                Double performancePercent = (performance / asset.getTotalPurchase()) * 100;
                asset.setPerformancePercent(performancePercent);
            } else {
                asset.setPerformancePercent(0.0);
            }
            
            assetRepository.save(asset);
        }
        
        // Actualizar resumen del portafolio
        updatePortfolioSummary();
    }

    /**
     * Actualiza el resumen del portafolio basado en los activos actuales
     */
    @Transactional
    public void updatePortfolioSummary() {
        PortfolioSummary summary = getOrCreateSummary();
        List<Asset> assets = assetRepository.findAll();
        
        // Calcular dinero invertido (total de compras)
        Double moneyInvested = assets.stream()
                .mapToDouble(Asset::getTotalPurchase)
                .sum();
        
        // Calcular valor total actual
        Double totalValue = assets.stream()
                .mapToDouble(Asset::getTotalCurrent)
                .sum();
        
        // Calcular rendimiento total
        Double performance = assets.stream()
                .mapToDouble(Asset::getPerformance)
                .sum();
        
        // Calcular porcentaje de rendimiento
        Double performancePercent = moneyInvested > 0 
                ? (performance / moneyInvested) * 100 
                : 0.0;
        
        summary.setMoneyInvested(moneyInvested);
        summary.setTotalValue(totalValue);
        summary.setPerformance(performance);
        summary.setPerformancePercent(performancePercent);
        
        portfolioSummaryRepository.save(summary);
    }

    private PortfolioSummaryDTO convertToSummaryDTO(PortfolioSummary summary) {
        PortfolioSummaryDTO dto = new PortfolioSummaryDTO();
        dto.setMoneyDeposited(summary.getMoneyDeposited());
        dto.setLiquidMoney(summary.getLiquidMoney());
        dto.setMoneyInvested(summary.getMoneyInvested());
        dto.setPerformance(summary.getPerformance());
        dto.setTotalValue(summary.getTotalValue());
        dto.setPerformancePercent(summary.getPerformancePercent());
        return dto;
    }

    private AssetDTO convertToAssetDTO(Asset asset) {
        AssetDTO dto = new AssetDTO();
        dto.setId(asset.getId().toString());
        dto.setSymbol(asset.getSymbol());
        dto.setName(asset.getName());
        dto.setType(asset.getType());
        dto.setUnits(asset.getUnits());
        dto.setPurchasePrice(asset.getPurchasePrice());
        dto.setTotalPurchase(asset.getTotalPurchase());
        dto.setCurrentPrice(asset.getCurrentPrice());
        dto.setTotalCurrent(asset.getTotalCurrent());
        dto.setPerformance(asset.getPerformance());
        dto.setPerformancePercent(asset.getPerformancePercent());
        return dto;
    }

    private SoldAssetDTO convertToSoldAssetDTO(SoldAsset soldAsset) {
        SoldAssetDTO dto = new SoldAssetDTO();
        dto.setId(soldAsset.getId().toString());
        dto.setSymbol(soldAsset.getSymbol());
        dto.setName(soldAsset.getName());
        dto.setType(soldAsset.getType());
        dto.setUnits(soldAsset.getUnits());
        dto.setPurchasePrice(soldAsset.getPurchasePrice());
        dto.setSalePrice(soldAsset.getSalePrice());
        dto.setSaleDate(soldAsset.getSaleDate().toString());
        dto.setPerformance(soldAsset.getPerformance());
        dto.setPerformancePercent(soldAsset.getPerformancePercent());
        return dto;
    }
}

