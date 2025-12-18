package com.portfolio.config;

import com.portfolio.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduler para actualizar precios de acciones automáticamente
 * Por defecto actualiza cada 5 minutos
 */
@Component
public class PriceUpdateScheduler {

    @Autowired
    private PortfolioService portfolioService;

    /**
     * Actualiza los precios cada 5 minutos
     * Para cambiar el intervalo, modifica el valor en fixedRate
     * Ejemplos:
     * - fixedRate = 300000 (5 minutos en milisegundos)
     * - fixedRate = 600000 (10 minutos)
     * - fixedRate = 3600000 (1 hora)
     */
    @Scheduled(fixedRate = 300000) // 5 minutos = 300000 ms
    public void updatePrices() {
        try {
            portfolioService.updateAssetPrices();
            System.out.println("Precios actualizados automáticamente: " + java.time.LocalDateTime.now());
        } catch (Exception e) {
            System.err.println("Error al actualizar precios automáticamente: " + e.getMessage());
        }
    }
}
