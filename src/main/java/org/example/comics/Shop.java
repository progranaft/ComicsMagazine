package org.example.comics;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

public class Shop implements Serializable {
    protected HashMap<Comics, ComicsData> comicsHashMap;
    protected HashSet<User> users;
    public Shop(){
        this.comicsHashMap = new HashMap<>();
        this.users = new HashSet<>();
    }

    public void addComics(Comics comics){
        if (comicsHashMap.containsKey(comics)) {
            Integer tmp = comicsHashMap.get(comics).getQuantity();
            Scanner input = new Scanner(System.in);
            tmp += input.nextInt();
            comicsHashMap.get(comics).setQuantity(tmp);
        } else {
            System.out.println("Введите цену закупки: ");
            Double coastPrice = Inputs.inputDouble();
            System.out.println("введите цену продажи: ");
            Double price = Inputs.inputDouble();
            System.out.println("Введите ID продукта: ");
            Long id = Inputs.inputLong();
            System.out.println("Введите количество: ");
            Integer quan = Inputs.inputInt();
            comicsHashMap.put(comics, new ComicsData(quan ,LocalDate.now(), coastPrice, price, id));
        }
    }

    public String showUsersList(){
        StringBuilder str = new StringBuilder();
        for (User user : this.users) {
            str.append(user.toString()).append("\n");
        }
        return str.toString();
    }

    public String showComicsList() {
        StringBuilder str = new StringBuilder();
        for (Map.Entry<Comics, ComicsData> comic:comicsHashMap.entrySet()) {
            str.append(comic.getKey().toString()).append(comic.getValue().toString()).append("\n");
        }
        return str.toString();
    }

    public void deleteComics(String name) {
        Comics comics = null;
        for (Map.Entry<Comics, ComicsData> comic : comicsHashMap.entrySet()) {
            if (comic.getKey().getName().equals(name)) {
                comics = comic.getKey();
                break;
            }
        }
        if (comics != null) {
            if (comicsHashMap.get(comics).getQuantity() > 1) {
                Integer tmp = comicsHashMap.get(comics).getQuantity();
                tmp--;
                comicsHashMap.get(comics).setQuantity(tmp);
            } else {
                comicsHashMap.remove(comics);
            }
            System.out.println("Комикс успешно удален");
        } else {
            System.out.println("Комикс не найден");
        }
    }

    public HashSet<User> getUsers() {
        return users;
    }

    public void setUsers(HashSet<User> users) {
        this.users = users;
    }

    public HashMap<Comics, ComicsData> searchComicsName(String name){
        HashMap<Comics, ComicsData> comicsList = new HashMap<>();
        for (Map.Entry<Comics, ComicsData> comics : comicsHashMap.entrySet()){
            if (comics.getKey().getName().contains(name)) {
                comicsList.put(comics.getKey(), comics.getValue());
            }
        }
        return comicsList;
    }

    public HashMap<Comics, ComicsData> searchComicsAuthor(String author){
        HashMap<Comics, ComicsData> comicsList = new HashMap<>();
        for (Map.Entry<Comics, ComicsData> comics : comicsHashMap.entrySet()){
            if (comics.getKey().getAuthor().contains(author)) {
                comicsList.put(comics.getKey(), comics.getValue());
            }
        }
        return comicsList;
    }

    public HashMap<Comics, ComicsData> searchComicsGenre(String genre){
        HashMap<Comics, ComicsData> comicsList = new HashMap<>();
        for (Map.Entry<Comics, ComicsData> comics : comicsHashMap.entrySet()){
            if (comics.getKey().getGenre().contains(genre)) {
                comicsList.put(comics.getKey(), comics.getValue());
            }
        }
        return comicsList;
    }

    public String searchComics(String searchStr) {
        HashMap<Comics, ComicsData> comicsList = new HashMap<>();
        comicsList.putAll(searchComicsName(searchStr));
        comicsList.putAll(searchComicsAuthor(searchStr));
        comicsList.putAll(searchComicsGenre(searchStr));
        String str = new String();
        for (Map.Entry<Comics, ComicsData> comics : comicsList.entrySet()) {
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
