package org.example.comics;

import java.io.Serializable;
import java.time.LocalDate;

public class ComicsLegacy extends Comics implements Serializable {
    private Comics legacy;

    public ComicsLegacy(){

    }
    public ComicsLegacy(String author, String designer, String name, LocalDate publicationDate, int pages, String publishingHouse, String genre, double costPrice, double price, Comics legacy) {
        super(author, designer, name, publicationDate, pages, publishingHouse, genre, costPrice, price);
        this.legacy = legacy;
    }

    @Override
    public String toString() {
        return "author=" + author +
                ", designer=" + designer +
                ", name=" + name +
                ", publicationDate=" + publicationDate +
                ", pages=" + pages +
                ", publishingHouse=" + publishingHouse +
                ", genre=" + genre +
                ", costPrice=" + costPrice +
                ", price=" + price +
                ", legacy=" + legacy.getName();
    }

    public Comics getLegacy() {
        return legacy;
    }

    public void setLegacy(Comics legacy) {
        this.legacy = legacy;
    }
}
