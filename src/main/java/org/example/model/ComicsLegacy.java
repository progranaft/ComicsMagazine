package org.example.model;

import org.example.model.Comics;

import java.io.Serializable;
import java.time.LocalDate;

public class ComicsLegacy extends Comics implements Serializable {
    private Comics legacy;

    public ComicsLegacy(){

    }
    public ComicsLegacy(String author, String designer, String name, LocalDate publicationDate, int pages, String publishingHouse, String genre,String fabricId, Comics legacy) {
        super(author, designer, name, publicationDate, pages, publishingHouse, genre, fabricId);
        this.legacy = legacy;
    }

    @Override
    public String toString() {
        return "ComicsLegacy{" +
                "legacy=" + legacy.getName() +
                ", fabricId='" + fabricId + '\'' +
                ", author='" + author + '\'' +
                ", designer='" + designer + '\'' +
                ", name='" + name + '\'' +
                ", publicationDate=" + publicationDate +
                ", pages=" + pages +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    public Comics getLegacy() {
        return legacy;
    }

    public void setLegacy(Comics legacy) {
        this.legacy = legacy;
    }

}
