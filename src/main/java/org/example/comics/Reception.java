package org.example.comics;

import org.example.menu.Menu;
import org.example.model.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Reception implements Serializable {
    HashMap<User, String> accounts;
    public Reception() {
        this.accounts = new HashMap<>();

        User admin = new User("Sasha");
        admin.setAdministrator(true);
        this.accounts.put(admin, "qwert");
//        User test = new User("Test");
//        test.setAdministrator(true);
//        this.accounts.put(test, "12345");
//        User test2 = new User("Test12312");
//        test2.setAdministrator(true);
//        this.accounts.put(test2, "12345");
    }

    public User authorization(MainController mainController) {
        User res = new User();
        while(true) {
            System.out.println("Добро пожаловать в Магазин Комиксов!");
            Menu recMenu = new Menu(res, (user)->"1. Вход\n2. Регистрация\n3. О программе\n4. Выход");
            Integer choice = recMenu.showMenu();
            if (choice == null) continue;
            if (choice == 1) {
                User user = this.getUser();
                if (user != null) {
                    res = user;
                    break;
                } else {
                    System.out.println("Комбинация логин/пароль не распознана");
                    continue;
                }
            } else if (choice == 2) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите имя пользователя: ");
                String name = scanner.nextLine();
                System.out.println("Задайте пароль: ");
                String pass = scanner.nextLine();
                System.out.println("Повторите пароль: ");
                String pass2 = scanner.nextLine();
                if (pass.equals(pass2)) {
                    this.accounts.put(new User(name), pass);
                    System.out.println("Пользователь добавлен");
                    mainController.saveUsersFile();
                } else {
                    System.out.println("Пароли не совпадают");
                }
            } else if (choice == 3) {
                mainController.soundBuffer.getBackGround().stopPlay();
                mainController.soundBuffer.getIntro().startPlay();
                String text = new String("Экзаменационная работа \"Магазин Комиксов\"\n" +
                        "Выполнил студент академии ТОП - ***** Александр\n" +
                        "Музыка на фоне:\n" +
                        "Kupla - Sea of Trees\n" +
                        "OST Battle toads and Double Dragon\n" +
                        "Воспроизводится с помощью javazoom » jlayer https://mvnrepository.com/artifact/javazoom/jlayer/1.0.1\n" +
                        "Скорость текста специально подбирал чтоб дослушать музыку до конца xD\n" +
                        "Ну все, пока, жми Enter ;)");
                for (int i = 0; i < text.length(); i++){
                    System.out.print(text.charAt(i));
                    try {
                        TimeUnit.MILLISECONDS.sleep(70);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine();
                mainController.soundBuffer.getIntro().stopPlay();
                mainController.soundBuffer.getBackGround().startPlay();
            } else if (choice == 4) {
                return null;
            }
        }
        return res;
    }

    private User getUser(){
        String name = "";
        String pass = "";
        Scanner in = new Scanner(System.in);
        System.out.println("Введите имя: ");
        name = in.nextLine();
        System.out.println("Введите пароль: ");
        pass = in.nextLine();
        User tmp = null;
        User res = null;
        for (Map.Entry<User, String> item : this.accounts.entrySet()) {
            if (item.getKey().getName().equals(name)) {
                tmp = item.getKey();
                break;
            }
        }
        if (tmp != null) {
            if (this.accounts.get(tmp).equals(pass)) {
                res = tmp;
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Reception{" +
                "accounts=" + accounts +
                '}';
    }
}
