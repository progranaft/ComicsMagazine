package org.example.comics;

public class TopAuthor {
    private String name;
    private Integer countComics;

    public TopAuthor(String name, Integer countComics) {
        this.name = name;
        this.countComics = countComics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountComics() {
        return countComics;
    }

    public void setCountComics(Integer countComics) {
        this.countComics = countComics;
    }
}
