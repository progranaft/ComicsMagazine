package org.example.stock;

import org.example.comics.Inputs;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Stock implements Serializable {

    protected HashSet<StockSale> stockSales;

    public Stock() {
        this.stockSales = new HashSet<>();
    }
    public StockSale getStockSale(String name){
        StockSale res = null;
        for (StockSale stockSale:this.stockSales){
            if (stockSale.getName().equals(name)){
                res = stockSale;
            }
        }
        return res;
    }
    public void createStockSale(){
        StockSale stockSale = new StockSale();
        System.out.println("Введите название акции: ");
        Scanner scanner = new Scanner(System.in);
        stockSale.setName(scanner.nextLine());
        System.out.println("Добавьте краткое описание: ");
        stockSale.setDescription(scanner.nextLine());
        System.out.println("Укажите размер скидки в процентах:");
        stockSale.setDiscount((Inputs.inputInt().doubleValue())/100);
        System.out.println("Укажите дату начала акции (ГГГГ-ММ-ДД): ");
        stockSale.setStartActions(Inputs.inputDate());
        System.out.println("Укажите дату конца акции (ГГГГ-ММ-ДД): ");
        stockSale.setEndActions(Inputs.inputDate());
        stockSales.add(stockSale);
        System.out.println("Акция успешно создана.");
    }
    public void deleteStockSale(String name){
        Iterator iterator = this.stockSales.iterator();
        StockSale stockSale = null;
        while (iterator.hasNext()){
            stockSale = (StockSale) iterator.next();
            if (stockSale.getName().equals(name)) {
                iterator.remove();
                break;
            }
        }
    }
    public String showStockSales(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("%15s %6s %11s %11s %30s\n", "Название", "Скидка", "Начало", "Конец", "Инфо"));
        for (StockSale stockSale:this.stockSales){
            res.append(String.format("%15s %6s%% %11s %11s %30s\n", stockSale.getName(), (int)(stockSale.getDiscount()*100), stockSale.getStartActions(), stockSale.getEndActions(), stockSale.getDescription()));
        }
        return res.toString();
    }

    public HashSet<StockSale> getStockSales() {
        return stockSales;
    }

    public void setStockSales(HashSet<StockSale> stockSales) {
        this.stockSales = stockSales;
    }
}
