package org.example.comics;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Shop implements Serializable {
    protected ArrayList<Comics> comics;
    public Shop() {
        this.comics = new ArrayList<>();
    }
    public String showComicsList(){
        String str = new String();
        for (Comics comic:comics) {
            str += comic.toString() + "\n";
        }
        return str;
    }
    public void addComics(Comics comics){
        this.comics.add(comics);
    }

    public void deleteComics(String name) {
        for (int i = 0; i < this.comics.size(); i++) {
            if (this.comics.get(i).getName().equals(name)) {

            }
        }
    }
}
