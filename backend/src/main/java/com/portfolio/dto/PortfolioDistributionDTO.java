package com.portfolio.dto;

public class PortfolioDistributionDTO {
    private String symbol;
    private Double value;
    private Double percentage;

    public PortfolioDistributionDTO() {
    }

    public PortfolioDistributionDTO(String symbol, Double value, Double percentage) {
        this.symbol = symbol;
        this.value = value;
        this.percentage = percentage;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
