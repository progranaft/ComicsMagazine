package org.example.comics;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class StockSale implements Serializable {
    private String name;
    private String description;
    private LocalDate startActions;
    private LocalDate endActions;
    private Double discount;

    public StockSale(){

    }

    public StockSale(String name, String description, LocalDate startActions, LocalDate endActions, Double discount) {
        this.name = name;
        this.description = description;
        this.startActions = startActions;
        this.endActions = endActions;
        this.discount = discount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void addComicsStockSale(Comics comics){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartActions() {
        return startActions;
    }

    public void setStartActions(LocalDate startActions) {
        this.startActions = startActions;
    }

    public LocalDate getEndActions() {
        return endActions;
    }

    public void setEndActions(LocalDate endActions) {
        this.endActions = endActions;
    }
}
