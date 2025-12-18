package com.portfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "portfolio_summary")
public class PortfolioSummary {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Double moneyDeposited;
    
    @Column(nullable = false)
    private Double liquidMoney;
    
    @Column(nullable = false)
    private Double moneyInvested;
    
    @Column(nullable = false)
    private Double performance;
    
    @Column(nullable = false)
    private Double totalValue;
    
    @Column(nullable = false)
    private Double performancePercent;

    public PortfolioSummary() {
    }

    public PortfolioSummary(Long id, Double moneyDeposited, Double liquidMoney, Double moneyInvested, Double performance, Double totalValue, Double performancePercent) {
        this.id = id;
        this.moneyDeposited = moneyDeposited;
        this.liquidMoney = liquidMoney;
        this.moneyInvested = moneyInvested;
        this.performance = performance;
        this.totalValue = totalValue;
        this.performancePercent = performancePercent;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
