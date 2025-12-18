package com.portfolio.config;

import com.portfolio.model.*;
import com.portfolio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private SoldAssetRepository soldAssetRepository;

    @Autowired
    private PortfolioSummaryRepository portfolioSummaryRepository;

    @Override
    public void run(String... args) throws Exception {
        // Solo inicializar si la base de datos está vacía
        if (assetRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        // Crear resumen inicial (solo si no existe)
        if (portfolioSummaryRepository.count() == 0) {
            PortfolioSummary summary = new PortfolioSummary();
            summary.setMoneyDeposited(0.0);
            summary.setLiquidMoney(-32900.0);
            summary.setMoneyInvested(32900.0);
            summary.setPerformance(3520.0);
            summary.setTotalValue(2107.5);
            summary.setPerformancePercent(0.0);
            portfolioSummaryRepository.save(summary);
        }

        // Crear activos de ejemplo
        Asset msft = new Asset();
        msft.setSymbol("MSFT");
        msft.setName("Microsoft Corp.");
        msft.setType("Acción");
        msft.setUnits(30);
        msft.setPurchasePrice(280.0);
        msft.setTotalPurchase(8400.0);
        msft.setCurrentPrice(325.75);
        msft.setTotalCurrent(9772.5);
        msft.setPerformance(1372.5);
        msft.setPerformancePercent(16.34);
        assetRepository.save(msft);

        Asset al30 = new Asset();
        al30.setSymbol("AL30");
        al30.setName("Bono Argentina 2030");
        al30.setType("Bono");
        al30.setUnits(200);
        al30.setPurchasePrice(42.5);
        al30.setTotalPurchase(8500.0);
        al30.setCurrentPrice(45.8);
        al30.setTotalCurrent(9160.0);
        al30.setPerformance(660.0);
        al30.setPerformancePercent(7.76);
        assetRepository.save(al30);

        Asset tsla = new Asset();
        tsla.setSymbol("TSLA");
        tsla.setName("Tesla Inc.");
        tsla.setType("Acción");
        tsla.setUnits(25);
        tsla.setPurchasePrice(245.0);
        tsla.setTotalPurchase(6125.0);
        tsla.setCurrentPrice(238.2);
        tsla.setTotalCurrent(5955.0);
        tsla.setPerformance(-170.0);
        tsla.setPerformancePercent(-2.78);
        assetRepository.save(tsla);

        Asset t30 = new Asset();
        t30.setSymbol("T-30");
        t30.setName("Bono USA Treasury");
        t30.setType("Bono");
        t30.setUnits(100);
        t30.setPurchasePrice(98.75);
        t30.setTotalPurchase(9875.0);
        t30.setCurrentPrice(101.2);
        t30.setTotalCurrent(10120.0);
        t30.setPerformance(245.0);
        t30.setPerformancePercent(2.48);
        assetRepository.save(t30);

        // Crear activo vendido de ejemplo
        SoldAsset aapl = new SoldAsset();
        aapl.setSymbol("AAPL");
        aapl.setName("Apple Inc.");
        aapl.setType("Acción");
        aapl.setUnits(50);
        aapl.setPurchasePrice(150.25);
        aapl.setSalePrice(178.5);
        aapl.setSaleDate(LocalDate.of(2025, 12, 17));
        aapl.setPerformance(1412.5);
        aapl.setPerformancePercent(18.8);
        soldAssetRepository.save(aapl);
    }
}

