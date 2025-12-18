package com.portfolio.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class StockPriceService {

    private final RestTemplate restTemplate;
    private final Random random = new Random();
    
    @Value("${stock.api.enabled:false}")
    private boolean apiEnabled;
    
    @Value("${stock.api.key:}")
    private String apiKey;
    
    // Precios base simulados para desarrollo
    private static final Map<String, Double> BASE_PRICES = new HashMap<>();
    
    static {
        BASE_PRICES.put("MSFT", 325.75);
        BASE_PRICES.put("TSLA", 238.20);
        BASE_PRICES.put("AAPL", 178.50);
        BASE_PRICES.put("AL30", 45.80);
        BASE_PRICES.put("T-30", 101.20);
    }

    public StockPriceService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Obtiene el precio actual de una acción o bono
     * @param symbol Símbolo de la acción (ej: MSFT, TSLA) o bono (ej: AL30, T-30)
     * @return Precio actual
     */
    public Double getCurrentPrice(String symbol) {
        if (apiEnabled && !apiKey.isEmpty()) {
            return fetchPriceFromAPI(symbol);
        } else {
            return getSimulatedPrice(symbol);
        }
    }

    /**
     * Obtiene precios desde Alpha Vantage API
     */
    private Double fetchPriceFromAPI(String symbol) {
        try {
            String url = String.format(
                "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=%s&apikey=%s",
                symbol, apiKey
            );
            
            @SuppressWarnings("unchecked")
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            
            if (response != null && response.containsKey("Global Quote")) {
                @SuppressWarnings("unchecked")
                Map<String, String> quote = (Map<String, String>) response.get("Global Quote");
                String priceStr = quote.get("05. price");
                if (priceStr != null && !priceStr.isEmpty()) {
                    return Double.parseDouble(priceStr);
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching price from API for " + symbol + ": " + e.getMessage());
        }
        
        // Fallback a precio simulado si la API falla
        return getSimulatedPrice(symbol);
    }

    /**
     * Genera un precio simulado basado en el precio base con variación aleatoria
     */
    private Double getSimulatedPrice(String symbol) {
        Double basePrice = BASE_PRICES.get(symbol);
        
        if (basePrice == null) {
            // Si no hay precio base, generar uno aleatorio
            basePrice = 100.0 + random.nextDouble() * 200.0;
        }
        
        // Aplicar variación aleatoria de ±5%
        double variation = (random.nextDouble() - 0.5) * 0.10; // -5% a +5%
        double newPrice = basePrice * (1 + variation);
        
        // Redondear a 2 decimales
        return Math.round(newPrice * 100.0) / 100.0;
    }

    /**
     * Actualiza el precio base para un símbolo (útil para testing)
     */
    public void updateBasePrice(String symbol, Double price) {
        BASE_PRICES.put(symbol, price);
    }
}

