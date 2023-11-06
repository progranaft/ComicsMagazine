package org.example.journal;

import org.example.model.Comics;
import org.example.model.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class SaleJournal implements Serializable {

    private ArrayList<SaleRecord> saleRecords;
    public SaleJournal(){
        this.saleRecords = new ArrayList<>();
    }


    public void addSaleRecords(Comics comics, User user, Integer quantity, Double salePrice){
        SaleRecord saleRecord = new SaleRecord(comics, user, quantity, LocalDate.now(), salePrice);
        saleRecords.add(saleRecord);
    }

    public ArrayList<SaleRecord> getSaleRecords() {
        return saleRecords;
    }

    public void setSaleRecords(ArrayList<SaleRecord> saleRecords) {
        this.saleRecords = saleRecords;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("| %15s | %20s | %15s | %6s | %5s |\n",
                "Дата продажи", "Комикс", "Покупатель", "Кол-во", "Стоимость"));
        for (SaleRecord saleRecord:saleRecords){
            res.append(String.format("| %15s | %20s | %15s | %6s | %5s |\n",
                    saleRecord.getSaleDate(), saleRecord.getComics().getName(), saleRecord.getUser().getName(), saleRecord.getQuantity(), saleRecord.getTransactionPrice()));
        }
        return res.toString();
    }
}
