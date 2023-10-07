package org.example.comics;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
    protected ArrayList<Comics> comics;
    public Shop() {
        this.comics = new ArrayList<>();
    }
    public void addComics(Comics comics) {
        this.comics.add(comics);
    }
    public String showComicsList(){
        String str = new String();
        for (Comics comic:comics) {
            str += comic.toString() + "\n";
        }
        return str;
    }
    public void createAndAddComics(){
        Comics comics;
        boolean legacy = false;
        int choice;
        while (true) {
            System.out.println("Добавляем продолжение комикса?" +
                    "\n1. Да" +
                    "\n2. Нет");
            Scanner scan = new Scanner(System.in);
            try {
                choice = scan.nextInt();
                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    continue;
                }
            } catch (Exception exception) {
                System.out.println("Ошибка ввода. Введите цифру.");
            }
        }
        if (choice == 1) {
            legacy = true;
            comics = new ComicsLegacy();
        } else {
            comics = new Comics();
        }
        Scanner scan = new Scanner(System.in);
        if (legacy) {
            System.out.println("Введите название предыстории:");
            String str = new String();
            str = scan.nextLine();
            Comics cms = null;
            for (Comics comic:this.comics) {
                if (comic.getName().equals(str)) {
                    cms = comic;
                    break;
                }
            }
            ComicsLegacy comicsL = (ComicsLegacy) comics;
            comicsL.setLegacy(cms);
            comics = (Comics) comicsL;
        }
        System.out.println("Введите автора:");
        comics.setAuthor(scan.nextLine());
        System.out.println("Введите дизайнера:");
        comics.setDesigner(scan.nextLine());
        System.out.println("Введите название:");
        comics.setName(scan.nextLine());
        System.out.println("Введите дату (ГГГГ-ММ-ДД):");
        comics.setPublicationDate(LocalDate.parse(scan.nextLine()));
        System.out.println("Введите количество страниц:");
        comics.setPages(Integer.parseInt(scan.nextLine()));
        System.out.println("Введите издательство:");
        comics.setPublishingHouse(scan.nextLine());
        System.out.println("Введите жанр:");
        comics.setGenre(scan.nextLine());
        System.out.println("Введите цену закупки:");
        comics.setCostPrice(Double.parseDouble(scan.nextLine()));
        System.out.println("Введите цену продажи:");
        comics.setPrice(Double.parseDouble(scan.nextLine()));
        this.comics.add(comics);
//        if (legacy) {
//            System.out.println("Введите название предыстории:");
//            String str = new String();
//            str = scan.nextLine();
//            Comics cms = null;
//            for (Comics comic:this.comics) {
//                if (comic.getName().equals(str)) {
//                    cms = comic;
//                    break;
//                }
//            }
//            ComicsLegacy comicsL = (ComicsLegacy) comics;
//            comicsL.setLegacy(cms);
//            this.comics.add(comicsL);
//        } else {
//            this.comics.add(comics);
//        }
    }
}
