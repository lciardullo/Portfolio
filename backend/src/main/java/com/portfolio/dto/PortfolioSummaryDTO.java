package com.portfolio.dto;

public class PortfolioSummaryDTO {
    private Double moneyDeposited;
    private Double liquidMoney;
    private Double moneyInvested;
    private Double performance;
    private Double totalValue;
    private Double performancePercent;

    public PortfolioSummaryDTO() {
    }

    public PortfolioSummaryDTO(Double moneyDeposited, Double liquidMoney, Double moneyInvested, Double performance, Double totalValue, Double performancePercent) {
        this.moneyDeposited = moneyDeposited;
        this.liquidMoney = liquidMoney;
        this.moneyInvested = moneyInvested;
        this.performance = performance;
        this.totalValue = totalValue;
        this.performancePercent = performancePercent;
    }

    public Double getMoneyDeposited() {
        return moneyDeposited;
    }

    public void setMoneyDeposited(Double moneyDeposited) {
        this.moneyDeposited = moneyDeposited;
    }

    public Double getLiquidMoney() {
        return liquidMoney;
    }

    public void setLiquidMoney(Double liquidMoney) {
        this.liquidMoney = liquidMoney;
    }

    public Double getMoneyInvested() {
        return moneyInvested;
    }

    public void setMoneyInvested(Double moneyInvested) {
        this.moneyInvested = moneyInvested;
    }

    public Double getPerformance() {
        return performance;
    }

    public void setPerformance(Double performance) {
        this.performance = performance;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Double getPerformancePercent() {
        return performancePercent;
    }

    public void setPerformancePercent(Double performancePercent) {
        this.performancePercent = performancePercent;
    }
}
