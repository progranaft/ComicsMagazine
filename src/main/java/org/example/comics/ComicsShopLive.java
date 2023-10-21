package org.example.comics;

import java.io.*;
import java.util.Scanner;

public class ComicsShopLive {
    Shop shop;
    FabricComics fc;
    Save save;
    public void start(){
        shop = null;
        fc = null;
        File saveFile = new File("save.txt");
        if (saveFile.exists()) {
            FileInputStream fin = null;
            ObjectInputStream oin = null;
            save = null;
            try {
                fin = new FileInputStream(saveFile);
                oin = new ObjectInputStream(fin);
                save = (Save) oin.readObject();
            } catch (Exception ex) {
                System.out.println("ошибка");
            } finally {
                try {
                    fin.close();
                } catch (IOException ex) {
                    System.out.println("шибка");
                }
            }
            shop = save.getShop();
            fc = save.getFc();
            System.out.println("Сохранение загружено");
        } else {
            shop = new Shop();
            fc = new FabricComics();
            System.out.println("Создан новый магазин");
        }

        Menu menu = new Menu(new MainMenu());

        while (true) {
            Integer choice = menu.showMenu();
            if (choice == 1) {
                Scanner scan = new Scanner(System.in);
                System.out.println("Введите название комикса:");
                String str = scan.nextLine();
                Comics cms = fc.checkComics(str);
                if (cms != null) {
                    shop.addComics(cms);
                    System.out.println("Комикс добавлен в магазин");
                } else {
                    System.out.println("Комикс не найден, либо не существует");
                }
            } else if (choice == 2) {
                Scanner scan = new Scanner(System.in);
                System.out.println("Введите название удаляемого комикса");
                String str = scan.nextLine();
                shop.deleteComics(str);
            } else if (choice == 3) {
                System.out.println(shop.showComicsList());
            } else if (choice == 4) {
                this.save();
            } else if (choice == 5) {
                Menu fabricMenu = new Menu(new FabricMenu());
                while (true) {
                    Integer choice2 = fabricMenu.showMenu();
                    if (choice2 == 1) {
                        Comics comics = fc.createComics();
                        if (comics != null) {
                            Scanner in = new Scanner(System.in);
                            Menu addInMagazine = new Menu(()->"Добавить комикс в магазин?\n1. Да\n2. Нет");
                            Integer input = addInMagazine.showMenu();
                            if (input == 1) {
                                if (comics != null) {
                                    shop.addComics(comics);
                                }
                            }
                        }
                    } else if (choice2 == 2) {

                    } else if (choice2 == 3) {
                        System.out.println(fc.showComics());
                    } else if (choice2 == 4) {
                        Menu changeComicsMenu = new Menu(new ChangeComicsMenu());


                    } else if (choice2 == 0) {
                        break;
                    }
                }
            } else if (choice == 6) {
                System.out.println("Введите название/автора/жанр комикса");
                Scanner input = new Scanner(System.in);
                String str = input.nextLine();
                System.out.println(shop.searchComics(str));
                System.out.println("Для продолжения введите любой символ");
                str = input.nextLine();
                continue;
            } else if (choice == 0) {
//                Menu exitMenu = new Menu(new ShowMenu() {
//                    @Override
//                    public String showMenuOptions() {
//                        return "Сохранить?\n1. Да\n2. Нет";
//                    }
//                });
                Menu exitMenu = new Menu(()->"Сохранить?\n1. Да\n2. Нет");
                Integer input = exitMenu.showMenu();
                if (input == 1) {
                    this.save();
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

    public void save(){
        save = new Save(fc, shop);
        FileOutputStream fout = null;
        ObjectOutputStream oout = null;
        File file = new File("save.txt");
        if (!file.exists()){
            try {
                if( file.createNewFile() )
                    System.out.println("Файл сохранения создан");
                else
                    System.out.println("Something Wrong!");
                } catch (Exception ex) {
                    System.out.println("Ошибика");
                }
        } else {
            System.out.println("Сохранение уже существует");
        }

        try {
            fout = new FileOutputStream(file);
            oout = new ObjectOutputStream(fout);
            oout.writeObject(save);
            System.out.println("Сохранение успешно перезаписано");
        } catch (FileNotFoundException ex) {
            System.out.println("Файл не найден");
        } catch (IOException ex) {
            System.out.println("Ошибка ввода вывода");
        } finally {
            try {
                oout.close();
            } catch (Exception ex) {
                System.out.println("Ошбика");
            }
        }
    }
}
