package org.example.comics;

import sun.util.resources.LocaleData;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Comics implements Serializable {
    protected String author;
    protected String designer;
    protected String name;
    protected LocalDate publicationDate;
    protected int pages;
    protected String publishingHouse;
    protected String genre;
    protected double costPrice;
    protected double price;

    public Comics(){

    }
    public Comics(String author, String designer, String name, LocalDate publicationDate, int pages, String publishingHouse, String genre, double costPrice, double price) {
        this.author = author;
        this.designer = designer;
        this.name = name;
        this.publicationDate = publicationDate;
        this.pages = pages;
        this.publishingHouse = publishingHouse;
        this.genre = genre;
        this.costPrice = costPrice;
        this.price = price;
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
                ", price=" + price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comics comics = (Comics) o;
        return pages == comics.pages && Double.compare(costPrice, comics.costPrice) == 0 && Double.compare(price, comics.price) == 0 && Objects.equals(author, comics.author) && Objects.equals(designer, comics.designer) && Objects.equals(name, comics.name) && Objects.equals(publicationDate, comics.publicationDate) && Objects.equals(publishingHouse, comics.publishingHouse) && Objects.equals(genre, comics.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, designer, name, publicationDate, pages, publishingHouse, genre, costPrice, price);
    }
}
