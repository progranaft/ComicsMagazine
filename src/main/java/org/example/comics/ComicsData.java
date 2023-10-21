package org.example.comics;

import java.time.LocalDate;

public class ComicsData {
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
