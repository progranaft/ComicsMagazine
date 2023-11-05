package org.example.comics;

import org.example.comics.stock.StockSale;

import java.io.Serializable;
import java.time.LocalDate;

public class ComicsData implements Serializable {
    protected Integer quantity;
    protected LocalDate dataReceipts;
    protected Double coastPrice;
    protected Double salePrice;
    protected Double stockSalePrice;
    protected Long productId;
    protected StockSale stockSale;

    public ComicsData(Integer quantity, LocalDate dataReceipts, Double coastPrice, Double salePrice, Long productId) {
        this.quantity = quantity;
        this.dataReceipts = dataReceipts;
        this.coastPrice = coastPrice;
        this.salePrice = salePrice;
        this.productId = productId;
        this.stockSalePrice = null;
        this.stockSale = null;
    }

    @Override
    public String toString() {
        return  quantity + "\t" + dataReceipts + "\t" + coastPrice + "\t" + salePrice + "\t" + productId;
    }

    public Double transactionPrice() {
        Double res = null;
        if (this.stockSale != null && LocalDate.now().isAfter(this.stockSale.getStartActions()) && LocalDate.now().isBefore(this.stockSale.getEndActions())) {
            res = this.stockSalePrice;
        } else {
            res = this.salePrice;
        }
        return res;
    }
    public Double getStockSalePrice() {
        return stockSalePrice;
    }
    public void setStockSalePrice(Double stockSalePrice) {
        this.stockSalePrice = stockSalePrice;
    }

    public StockSale getStockSale() {
        return stockSale;
    }

    public void setStockSale(StockSale stockSale) {
        this.stockSale = stockSale;
        this.stockSalePrice = this.salePrice*(1-stockSale.getDiscount());
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
