package org.example.comics;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Shop implements Serializable {
    protected HashMap<Comics, Integer> comicsHashMap;

    public Shop(){
        this.comicsHashMap = new HashMap<>();
    }

    public void addComics(Comics comics){
        if (comicsHashMap.containsKey(comics)) {
            Integer tmp = comicsHashMap.get(comics);
            tmp++;
            comicsHashMap.put(comics, tmp);
        } else {
            comicsHashMap.put(comics, 1);
        }
    }

    public String showComicsList() {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<Comics, Integer> comic:comicsHashMap.entrySet()) {
            str.append(comic.getKey().toString()).append(" Количество: ").append(comic.getValue()).append("\n");
        }
        return str.toString();
    }

    public void deleteComics(String name) {
        Comics comics = null;
        for (Map.Entry<Comics, Integer> comic : comicsHashMap.entrySet()) {
            if (comic.getKey().getName().equals(name)) {
                comics = comic.getKey();
                break;
            }
        }
        if (comics != null) {
            if (comicsHashMap.get(comics) > 1) {
                Integer tmp = comicsHashMap.get(comics);
                tmp--;
                comicsHashMap.put(comics, tmp);
            } else {
                comicsHashMap.remove(comics);
            }
            System.out.println("Комикс учпешно удален");
        } else {
            System.out.println("Комикс не найден");
        }
    }

    public HashMap<Comics, Integer> searchComicsName(String name){
        //ArrayList<Comics> comicsList = new ArrayList<>();
        HashMap<Comics, Integer> comicsList = new HashMap<>();
        for (Map.Entry<Comics, Integer> comics : comicsHashMap.entrySet()){
            if (comics.getKey().getName().contains(name)) {
                comicsList.put(comics.getKey(), comics.getValue());
                //comicsList.add((Comics) comics);
            }
        }
        return comicsList;
    }

    public HashMap<Comics, Integer> searchComicsAuthor(String author){
        //ArrayList<Comics> comicsList = new ArrayList<>();
        HashMap<Comics, Integer> comicsList = new HashMap<>();
        for (Map.Entry<Comics, Integer> comics : comicsHashMap.entrySet()){
            if (comics.getKey().getAuthor().contains(author)) {
                comicsList.put(comics.getKey(), comics.getValue());
                //comicsList.add((Comics) comics);
            }
        }
        return comicsList;
    }

    public HashMap<Comics, Integer> searchComicsGenre(String genre){
        //ArrayList<Comics> comicsList = new ArrayList<>();
        HashMap<Comics, Integer> comicsList = new HashMap<>();
        for (Map.Entry<Comics, Integer> comics : comicsHashMap.entrySet()){
            if (comics.getKey().getGenre().contains(genre)) {
                comicsList.put(comics.getKey(), comics.getValue());
                //comicsList.add((Comics) comics);
            }
        }
        return comicsList;
    }

    public String searchComics(String searchStr) {
        HashMap<Comics, Integer> comicsList = new HashMap<>();
        comicsList.putAll(searchComicsName(searchStr));
        comicsList.putAll(searchComicsAuthor(searchStr));
        comicsList.putAll(searchComicsGenre(searchStr));
        String str = new String();
        for (Map.Entry<Comics, Integer> comics : comicsList.entrySet()) {
            str += comics.getKey().toString() + " Количество: " + comics.getValue() + "\n";
        }
        return str;
    }

//    protected ArrayList<Comics> comics;
//    public Shop() {
//        this.comics = new ArrayList<>();
//    }
//    public String showComicsList(){
//        String str = new String();
//        for (Comics comic:comics) {
//            str += comic.toString() + "\n";
//        }
//        return str;
//    }
//    public void addComics(Comics comics){
//        this.comics.add(comics);
//    }
//
//    public void deleteComics(String name) {
//        int index = -1;
//        for (int i = 0; i < this.comics.size(); i++) {
//            if (this.comics.get(i).getName().equals(name)) {
//                index = i;
//                break;
//            }
//        }
//        if (index > -1) {
//            this.comics.remove(index);
//            System.out.println("Комикс удален");
//        } else {
//            System.out.println("Комикс не найден");
//        }
//    }
//
//    public void changeComics() {
//
//    }
}
