package com.portfolio.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MoneyTransactionDTO {
    @NotNull(message = "El monto es requerido")
    @Positive(message = "El monto debe ser positivo")
    private Double amount;

    public MoneyTransactionDTO() {
    }

    public MoneyTransactionDTO(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
