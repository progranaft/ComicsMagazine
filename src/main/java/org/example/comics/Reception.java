package org.example.comics;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Reception extends Thread {
    HashMap<User, String> accounts;
    public Reception() {
        this.accounts = new HashMap<>();
        User admin = new User("Sasha");
        admin.setAdministrator(true);
        this.accounts.put(admin, "qwert");
        User test = new User("Test");
        this.accounts.put(test, "12345");
    }

    @Override
    public void run() {
        this.go();
    }

    public void go() {
        while(true) {
            System.out.println("Добро пожаловать в Магазин Комиксов!");
            Menu recMenu = new Menu(()->"1. Вход\n2. Регистрация");
            Integer choice = recMenu.showMenu();
            if (choice == 1) {
                String name = "";
                String pass = "";
                Scanner in = new Scanner(System.in);
                System.out.println("Введите имя: ");
                name = in.nextLine();
                System.out.println("Введите пароль: ");
                pass = in.nextLine();
                User user = this.getUser(name, pass);
                if (user != null) {
                    ComicsShopLive cms = new ComicsShopLive(user);
                    cms.go();
                } else {
                    System.out.println("Комбинация логин/пароль не распознаны");
                    continue;
                }
            } else if (choice == 2) {

            }
        }
    }

    private User getUser(String name, String pass){
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

}
