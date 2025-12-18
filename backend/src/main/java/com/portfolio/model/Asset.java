package com.portfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "assets")
public class Asset {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String symbol;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String type; // "Acción" o "Bono"
    
    @Column(nullable = false)
    private Integer units;
    
    @Column(nullable = false)
    private Double purchasePrice;
    
    @Column(nullable = false)
    private Double totalPurchase;
    
    @Column(nullable = false)
    private Double currentPrice;
    
    @Column(nullable = false)
    private Double totalCurrent;
    
    @Column(nullable = false)
    private Double performance;
    
    @Column(nullable = false)
    private Double performancePercent;

    public Asset() {
    }

    public Asset(Long id, String symbol, String name, String type, Integer units, Double purchasePrice, Double totalPurchase, Double currentPrice, Double totalCurrent, Double performance, Double performancePercent) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.type = type;
        this.units = units;
        this.purchasePrice = purchasePrice;
        this.totalPurchase = totalPurchase;
        this.currentPrice = currentPrice;
        this.totalCurrent = totalCurrent;
        this.performance = performance;
        this.performancePercent = performancePercent;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(Double totalPurchase) {
        this.totalPurchase = totalPurchase;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getTotalCurrent() {
        return totalCurrent;
    }

    public void setTotalCurrent(Double totalCurrent) {
        this.totalCurrent = totalCurrent;
    }

    public Double getPerformance() {
        return performance;
    }

    public void setPerformance(Double performance) {
        this.performance = performance;
    }

    public Double getPerformancePercent() {
        return performancePercent;
    }

    public void setPerformancePercent(Double performancePercent) {
        this.performancePercent = performancePercent;
    }
}
