package org.example.comics;

import sun.util.resources.LocaleData;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class FabricComics implements Serializable {
    protected HashSet<Comics> comicsSet;
    public FabricComics(){
        this.comicsSet = new HashSet<>();
    }
    public Comics createComics() {
        Comics comics = null;
        Comics legacyComics = null;
        boolean legacy = false;
        while(true) { //выясняем легаси или нет
            int choice;
            while (true) {
                System.out.println("Создаем продолжение комикса?" +
                        "\n1. Да" +
                        "\n2. Нет" +
                        "\n3. Назад");
                Scanner scan = new Scanner(System.in);
                try {
                    choice = scan.nextInt();
                    if (choice == 1 || choice == 2 || choice == 3) {
                        break;
                    } else {
                        continue;
                    }
                } catch (Exception exception) {
                    System.out.println("Ошибка ввода. Введите цифру.");
                }
            }
            if (choice == 3) {
                return null;
            } else if (choice == 1) {
                System.out.println("Введите название предистории или \"назад\" для отмены:");
                String str;
                Scanner scan = new Scanner(System.in);
                str = scan.nextLine();
                if (str.equals("назад")) {
                    continue;
                }
                Iterator iterator = this.comicsSet.iterator();
                while (iterator.hasNext()) {
                    legacyComics = (Comics) iterator.next();
                    if (legacyComics.getName().equals(str)){
                        break;
                    }
                }
                if (legacyComics != null) {
                    legacy = true;
                    comics = new ComicsLegacy();
                    System.out.println("Предистория успешно найдена.");
                    break;
                } else {
                    System.out.println("Предистория НЕ найдена!");
                    continue;
                }
            } else if (choice == 2) {
                comics = new Comics();
                break;
            }
        }
        if (legacy) {
            ComicsLegacy comicsL = (ComicsLegacy) comics;
            comicsL.setLegacy(legacyComics);
            comics = (Comics) comicsL;
        }
        Scanner scan = new Scanner(System.in);
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
        comicsSet.add(comics);
        return comics;
    }

    public String showComics(){
        StringBuilder str = new StringBuilder();
        for (Comics comic:this.comicsSet) {
            str.append(comic.toString()).append("\n");
        }
        return str.toString();
    }

    public HashSet<Comics> getComicsSet(){
        return this.comicsSet;
    }

    public Comics checkComics(String str){
        Comics cms = null;
        for (Comics comic:this.comicsSet){
            if (comic.getName().equals(str)){
                cms = comic;
                break;
            }
        }
        return cms;
    }
    public Comics getAvengers(){
        Comics avengrs = new Comics("Стэн Ли", "Джек Кирби", "Avengers", LocalDate.of(1963, 9, 1), 150, "Marvel Comics", "Fantastic", 10, 30);
        this.comicsSet.add(avengrs);
        return avengrs;
    }
//    public static ComicsLegacy getAvengers2(){
//        return new ComicsLegacy("Стэн Ли", "Джек Кирби", "Avengers 2", LocalDate.of(1970, 3, 22), 175, "Marvel Comics", "Fantastic", 10, 35, getAvengers());
//    }
    public Comics getSpiderMan(){
        Comics spiderMan = new Comics("Стэн Ли", "Стив Дитко", "Spider-Man", LocalDate.of(1962, 7, 15), 278, "Marvel Comics", "Fantastic", 10, 40);
        this.comicsSet.add(spiderMan);
        return spiderMan;
    }
}
