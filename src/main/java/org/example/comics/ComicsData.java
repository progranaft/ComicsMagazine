package org.example.comics;

import java.io.Serializable;
import java.time.LocalDate;

public class ComicsData implements Serializable {
    protected Integer quantity;
    protected LocalDate dataReceipts;
    protected Double coastPrice;
    protected Double salePrice;
    protected Long productId;

    public ComicsData(Integer quantity, LocalDate dataReceipts, Double coastPrice, Double salePrice, Long productId) {
        this.quantity = quantity;
        this.dataReceipts = dataReceipts;
        this.coastPrice = coastPrice;
        this.salePrice = salePrice;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ComicsData{" +
                "quantity=" + quantity +
                ", dataReceipts=" + dataReceipts +
                ", coastPrice=" + coastPrice +
                ", salePrice=" + salePrice +
                ", productId=" + productId +
                '}';
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDataReceipts() {
        return dataReceipts;
    }

    public void setDataReceipts(LocalDate dataReceipts) {
        this.dataReceipts = dataReceipts;
    }

    public Double getCoastPrice() {
        return coastPrice;
    }

    public void setCoastPrice(Double coastPrice) {
        this.coastPrice = coastPrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
