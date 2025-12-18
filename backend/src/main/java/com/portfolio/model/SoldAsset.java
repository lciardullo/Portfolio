package com.portfolio.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "sold_assets")
public class SoldAsset {
    
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
    private Double salePrice;
    
    @Column(nullable = false)
    private LocalDate saleDate;
    
    @Column(nullable = false)
    private Double performance;
    
    @Column(nullable = false)
    private Double performancePercent;

    public SoldAsset() {
    }

    public SoldAsset(Long id, String symbol, String name, String type, Integer units, Double purchasePrice, Double salePrice, LocalDate saleDate, Double performance, Double performancePercent) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.type = type;
        this.units = units;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.saleDate = saleDate;
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

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
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
