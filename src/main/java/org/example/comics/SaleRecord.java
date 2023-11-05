package org.example.comics;

import org.example.model.Comics;
import org.example.model.User;

import java.io.Serializable;
import java.time.LocalDate;

public class SaleRecord implements Serializable {
    private Comics comics;
    private User user;
    private Integer quantity;
    private LocalDate saleDate;
    private Double transactionPrice;

    public SaleRecord(){

    }

    public SaleRecord(Comics comics, User user, Integer quantity, LocalDate saleDate, Double transactionPrice) {
        this.comics = comics;
        this.user = user;
        this.quantity = quantity;
        this.saleDate = saleDate;
        this.transactionPrice = transactionPrice;
    }

    public Comics getComics() {
        return comics;
    }

    public void setComics(Comics comics) {
        this.comics = comics;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Double getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(Double transactionPrice) {
        this.transactionPrice = transactionPrice;
    }
}
