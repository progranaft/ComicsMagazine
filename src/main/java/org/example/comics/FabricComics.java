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
        comics.setFabricId(this.generateId());
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите автора:");
        comics.setAuthor(scan.nextLine());
        System.out.println("Введите дизайнера:");
        comics.setDesigner(scan.nextLine());
        System.out.println("Введите название:");
        comics.setName(scan.nextLine());
        System.out.println("Введите дату (ГГГГ-ММ-ДД):");
        comics.setPublicationDate(Inputs.inputDate());
        System.out.println("Введите количество страниц:");
        comics.setPages(Inputs.inputInt());
        System.out.println("Введите издательство:");
        comics.setPublishingHouse(scan.nextLine());
        System.out.println("Введите жанр:");
        comics.setGenre(scan.nextLine());
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

    public String generateId(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int boolRand = (int)(Math.random()*2);
            if (boolRand == 1) {
                str.append((char)(int)(Math.random()*10 + 48));
            } else if (boolRand == 0) {
                str.append((char)(int)(Math.random()*26 + 65));
            }
        }
        return str.toString();
    }

    public int changeComics(){
        Comics comics = null;
        while(true){
            Scanner input = new Scanner(System.in);
            String str = new String();
            System.out.println("Введите название комикса или \"отмена\" для возврата:");
            str = input.nextLine();
            if (str.equals("отмена")) {
                return 0;
            } else {
                comics = this.checkComics(str);
                if (comics == null) {
                    System.out.println("Комикс не найден или не существует");
                    continue;
                } else {
                    break;
                }
            }
        }
        if (comics != null) {
            while(true){
                Scanner in = new Scanner(System.in);
                Menu changeMenu = new Menu(new User(), new ChangeComicsMenu());
                Integer choice = changeMenu.showMenu();
                if (choice == 1) {
                    System.out.println("Введите новое название:");
                    String str = in.nextLine();
                    comics.setName(str);
                    System.out.println("Название изменено");
                    continue;
                } else if (choice == 2) {
                    System.out.println("Введите нового автора:");
                    String str = in.nextLine();
                    comics.setAuthor(str);
                    System.out.println("Автор изменен");
                    continue;
                } else if (choice == 3) {
                    System.out.println("Введите нового дизайнера:");
                    String str = in.nextLine();
                    comics.setDesigner(str);
                    System.out.println("Дизайнер изменен");
                    continue;
                } else if (choice == 4) {
                    System.out.println("Введите новую дату (ГГГГ-ММ-ДД):");
                    comics.setPublicationDate(Inputs.inputDate());
                    System.out.println("Дата изменена");
                    continue;
                } else if (choice == 5) {
                    System.out.println("Введите новое кол-во страниц:");
                    Integer pages = Inputs.inputInt();
                    comics.setPages(pages);
                    System.out.println("Кол-во страниц изменено");
                    continue;
                } else if (choice == 6) {
                    System.out.println("Введите новое издательство:");
                    String str = in.nextLine();
                    comics.setPublishingHouse(str);
                    System.out.println("Издательство изменено");
                    continue;
                } else if (choice == 7) {
                    System.out.println("Введите жанр:");
                    String str = in.nextLine();
                    comics.setGenre(str);
                    System.out.println("Жанр изменен");
                    continue;
                } else if (choice == 0) {
                    break;
                }
            }

        }
        return 0;
    }


//    public Comics getAvengers(){
//        Comics avengrs = new Comics("Стэн Ли", "Джек Кирби", "Avengers", LocalDate.of(1963, 9, 1), 150, "Marvel Comics", "Fantastic", 10, 30);
//        this.comicsSet.add(avengrs);
//        return avengrs;
//    }
    public Comics getAvengers2(){
        Comics avengers2 = new Comics("Стэн Ли", "Джек Кирби", "Avengers 2", LocalDate.of(1970, 3, 22), 175, "Marvel Comics", "Fantastic", "ITJFNDRWEO");
        this.comicsSet.add(avengers2);
        return avengers2;
    }
    public Comics getSpiderMan(){
        Comics spiderMan = new Comics("Стэн Ли", "Стив Дитко", "Spider-Man", LocalDate.of(1962, 7, 15), 278, "Marvel Comics", "Fantastic", "ABCDEFGHIJ");
        this.comicsSet.add(spiderMan);
        return spiderMan;
    }
}
