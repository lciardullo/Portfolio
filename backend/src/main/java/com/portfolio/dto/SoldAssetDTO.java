package com.portfolio.dto;

public class SoldAssetDTO {
    private String id;
    private String symbol;
    private String name;
    private String type;
    private Integer units;
    private Double purchasePrice;
    private Double salePrice;
    private String saleDate;
    private Double performance;
    private Double performancePercent;

    public SoldAssetDTO() {
    }

    public SoldAssetDTO(String id, String symbol, String name, String type, Integer units, Double purchasePrice, Double salePrice, String saleDate, Double performance, Double performancePercent) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
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
