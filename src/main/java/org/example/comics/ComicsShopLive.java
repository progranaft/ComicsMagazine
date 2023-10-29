package org.example.comics;

import org.example.sounds.Sound;
import org.example.sounds.Sound2;

import java.io.*;
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
            this.goAdmin();
            //this.goUser();
        }
    }

    public void goAdmin(){
        //this.goShop();
        Menu menu = new Menu(new MainMenu());
        while (true) {
            Integer choice = menu.showMenu();
            if (choice == 1) {
                Scanner scan = new Scanner(System.in);
                System.out.println("Введите название комикса:");
                String str = scan.nextLine();
                Comics cms = mainController.fabricComics.checkComics(str);
                if (cms != null) {
                    mainController.shop.addComics(cms);
                    System.out.println("Комикс добавлен в магазин");
                } else {
                    System.out.println("Комикс не найден, либо не существует");
                }
            } else if (choice == 2) {
                Scanner scan = new Scanner(System.in);
                System.out.println("Введите название удаляемого комикса");
                String str = scan.nextLine();
                mainController.shop.deleteComics(str);
            } else if (choice == 3) {
                System.out.println(mainController.shop.showComicsList());
            } else if (choice == 4) {
                System.out.println(mainController.shop.showUsersList());
            } else if (choice == 5) {

                Menu fabricMenu = new Menu(new FabricMenu());
                while (true) {
                    Integer choice2 = fabricMenu.showMenu();
                    if (choice2 == 1) {
                        sound2 = new Sound2();
                        sound2.start();
                        Comics comics = mainController.fabricComics.createComics();
                        if (comics != null) {
                            Scanner in = new Scanner(System.in);
                            Menu addInMagazine = new Menu(()->"Добавить комикс в магазин?\n1. Да\n2. Нет");
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
                    } else if (choice2 == 4) {
                        mainController.fabricComics.changeComics();
                    } else if (choice2 == 0) {
                        break;
                    }
                }
            } else if (choice == 6) {

                System.out.println("Введите название/автора/жанр комикса");
                Scanner input = new Scanner(System.in);
                String str = input.nextLine();
                System.out.println(mainController.shop.searchComics(str));
                System.out.println("Для продолжения введите любой символ");
                str = input.nextLine();
                continue;
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
                Menu exitMenu = new Menu(()->"Сохранить?\n1. Да\n2. Нет");
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

//    public void goUser(){
//        this.goShop();
//        Menu menu = new Menu(new MainMenu());
//
//        while (true) {
//            Integer choice = menu.showMenu();
//            if (choice == 1) {
//                Scanner scan = new Scanner(System.in);
//                System.out.println("Введите название комикса:");
//                String str = scan.nextLine();
//                Comics cms = fc.checkComics(str);
//                if (cms != null) {
//                    shop.addComics(cms);
//                    System.out.println("Комикс добавлен в магазин");
//                } else {
//                    System.out.println("Комикс не найден, либо не существует");
//                }
//            } else if (choice == 2) {
//                Scanner scan = new Scanner(System.in);
//                System.out.println("Введите название удаляемого комикса");
//                String str = scan.nextLine();
//                shop.deleteComics(str);
//            } else if (choice == 3) {
//                System.out.println(shop.showComicsList());
//            } else if (choice == 4) {
//                System.out.println(shop.showUsersList());
//            } else if (choice == 5) {
//
//                Menu fabricMenu = new Menu(new FabricMenu());
//                while (true) {
//                    Integer choice2 = fabricMenu.showMenu();
//                    if (choice2 == 1) {
//                        sound2 = new Sound2();
//                        sound2.start();
//                        Comics comics = fc.createComics();
//                        if (comics != null) {
//                            Scanner in = new Scanner(System.in);
//                            Menu addInMagazine = new Menu(()->"Добавить комикс в магазин?\n1. Да\n2. Нет");
//                            Integer input = addInMagazine.showMenu();
//                            if (input == 1) {
//                                if (comics != null) {
//                                    shop.addComics(comics);
//                                }
//                            }
//                        }
//                        sound2.getClip().stop();
//                        sound2.getClip().close();
//                        sound = null;
//                    } else if (choice2 == 2) {
//
//                    } else if (choice2 == 3) {
//                        System.out.println(fc.showComics());
//                    } else if (choice2 == 4) {
//                        this.fc.changeComics();
//                    } else if (choice2 == 0) {
//                        break;
//                    }
//                }
//            } else if (choice == 6) {
//
//                System.out.println("Введите название/автора/жанр комикса");
//                Scanner input = new Scanner(System.in);
//                String str = input.nextLine();
//                System.out.println(shop.searchComics(str));
//                System.out.println("Для продолжения введите любой символ");
//                str = input.nextLine();
//                continue;
//            } else if (choice == 9) {
//                this.save();
//            } else if (choice == 0) {
//                Menu exitMenu = new Menu(()->"Сохранить?\n1. Да\n2. Нет");
//                Integer input = exitMenu.showMenu();
//                if (input == 1) {
//                    this.save();
//                    break;
//                } if (input == 2){
//                    break;
//                } else {
//                    continue;
//                }
//            } else {
//                continue;
//            }
//        }
//    }

}
