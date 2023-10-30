package org.example.comics;

import org.example.sounds.Sound;
import org.example.sounds.Sound2;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ComicsShopLive {
    Sound sound;
    Sound2 sound2;
    MainController mainController;
    public ComicsShopLive(MainController mainController){
        this.mainController = mainController;
    }

    public void go(){
        if (mainController.user.isAdministrator()) {
            this.goAdmin();
        } else {
            //this.goAdmin();
            this.goUser();
        }
    }

    public void goAdmin(){
        //Админская менюшка
        Menu menu = new Menu(mainController.user , new MainMenu());
        while (true) {
            Integer choice = menu.showMenu();
            if (choice == 1) { //Меню работы с магазином
                Menu magazineMenu = new Menu(mainController.user, new MagazineMenu());
                while (true){
                    Integer choice3 = magazineMenu.showMenu();
                    if (choice3 == 1) { //Добавление комикса в магазин
                        System.out.println(this.addComicsInShop());
                    } else if (choice3 == 2) { //Удаление комиксса из магазина
                        Scanner scan = new Scanner(System.in);
                        System.out.println("Введите название удаляемого комикса");
                        String str = scan.nextLine();
                        mainController.shop.deleteComics(str);
                    } else if (choice3 == 3) { //Список всех комиксов в магазине
                        System.out.println(mainController.shop.showAllComics(mainController.user));
                    } else if (choice3 == 4) { //Поиск комиксов по магазину
                        System.out.println("Введите название/автора/жанр комикса");
                        Scanner input = new Scanner(System.in);
                        String str = input.nextLine();
                        System.out.println(mainController.shop.searchComics(str, mainController.user));
                        System.out.println("Для продолжения введите любой символ");
                        str = input.nextLine();
                        continue;
                    } else if (choice3 == 5) { //Список новых поступлений
                        System.out.println(mainController.shop.getPremierComics(mainController.user));
                    } else if (choice3 == 6) { //Список новинок
                        System.out.println(mainController.shop.getNewComics(mainController.user));
                    } else if (choice3 == 0) { //Возврат на предыдущую старницу меню
                        break;
                    }
                }
            } else if (choice == 2) { // Меню базы комиксов
                Menu fabricMenu = new Menu(mainController.user, new FabricMenu());
                while (true) {
                    Integer choice2 = fabricMenu.showMenu();
                    if (choice2 == 1) {
                        sound2 = new Sound2();
                        sound2.start();
                        Comics comics = mainController.fabricComics.createComics();
                        if (comics != null) {
                            Scanner in = new Scanner(System.in);
                            Menu addInMagazine = new Menu(mainController.user, (user)->"Добавить комикс в магазин?\n1. Да\n2. Нет");
                            Integer input = addInMagazine.showMenu();
                            if (input == 1) {
                                if (comics != null) {
                                    mainController.shop.addComics(comics);
                                }
                            }
                        }
                        sound2.getClip().stop();
                        sound2.getClip().close();
                        sound = null;
                    } else if (choice2 == 2) {

                    } else if (choice2 == 3) {
                        System.out.println(mainController.fabricComics.showComics());
                    } else if (choice2 == 4) { // Изменение комиксов
                        mainController.fabricComics.changeComics();
                    } else if (choice2 == 0) {
                        break;
                    }
                }
            } else if (choice == 3) {

            } else if (choice == 4) {

            } else if (choice == 5) {

            } else if (choice == 6) {



            } else if (choice == 9) {
                mainController.save();
            } else if (choice == 879546) {
                if (sound == null) {
                    Inputs.pegs = true;
                    sound = new Sound();
                    sound.start();
                    //Inputs.funky();
                } else {
                    Inputs.pegs = false;
                    sound.getClip().stop();
                    sound.getClip().close();
                    sound.interrupt();
                    sound = null;
                }
            } else if (choice == 0) {
                Menu exitMenu = new Menu(mainController.user ,(user)->"Сохранить?\n1. Да\n2. Нет");
                Integer input = exitMenu.showMenu();
                if (input == 1) {
                    mainController.save();
                    break;
                } if (input == 2){
                    break;
                } else {
                    continue;
                }
            } else if (choice == 123) {
                this.addTestComics();
            } else {
                continue;
            }
        }
    }

    private String addComicsInShop(){
        String res = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите название комикса:");
        String str = scan.nextLine();
        Comics cms = mainController.fabricComics.checkComics(str);
        if (cms != null) {
            mainController.shop.addComics(cms);
            res = "Комикс добавлен в магазин";
        } else {
            res = "Комикс не найден, либо не существует";
        }
        return res;
    }

    public void goUser(){
        Menu menu = new Menu(mainController.user , new MainMenu());
        while (true) {
            Integer choice = menu.showMenu();
            if (choice == 1) { //Меню работы с магазином
                Menu magazineMenu = new Menu(mainController.user, new MagazineMenu());
                while (true){
                    Integer choice3 = magazineMenu.showMenu();
                    if (choice3 == 1) { //Список всех комиксов в магазине
                        System.out.println(mainController.shop.showAllComics(mainController.user));
                    } else if (choice3 == 2) { //Поиск комиксов по магазину
                        System.out.println("Введите название/автора/жанр комикса");
                        Scanner input = new Scanner(System.in);
                        String str = input.nextLine();
                        System.out.println(mainController.shop.searchComics(str, mainController.user));
                        System.out.println("Для продолжения введите любой символ");
                        str = input.nextLine();
                        continue;
                    } else if (choice3 == 3) { //Список новых поступлений
                        System.out.println(mainController.shop.getPremierComics(mainController.user));
                    } else if (choice3 == 4) { //Список новинок
                        System.out.println(mainController.shop.getNewComics(mainController.user));
                    } else if (choice3 == 0) { //Возврат на предыдущую старницу меню
                        break;
                    }
                }
            } else if (choice == 2) {



            } else if (choice == 3) {



            } else if (choice == 4) {



            } else if (choice == 5) {



            } else if (choice == 6) {



            } else if (choice == 9) {
                mainController.save();
            } else if (choice == 0) {
                Menu exitMenu = new Menu(mainController.user ,(user)->"Сохранить?\n1. Да\n2. Нет");
                Integer input = exitMenu.showMenu();
                if (input == 1) {
                    mainController.save();
                    break;
                } if (input == 2){
                    break;
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
    }

    public void addTestComics(){
        mainController.shop.comicsHashMap.put(mainController.fabricComics.getSpiderMan(), new ComicsData(9, LocalDate.of(2022, 10, 1), 12.0, 34.0, 12345L));
        mainController.shop.comicsHashMap.put(mainController.fabricComics.getAvengers2(), new ComicsData(7, LocalDate.of(2023, 5, 23), 10.0, 53.0, 1234523L));
    }

}
