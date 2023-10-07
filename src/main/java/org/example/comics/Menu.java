package org.example.comics;

import java.util.Scanner;

public class Menu {
    protected MenuOptions menuOptions;
    public Menu(MenuOptions options){
        this.menuOptions = options;
    }
    public Integer showMenu(){
        Integer choice = null;
        while (true) {
            System.out.println(this.menuOptions);
            Scanner scanner = new Scanner(System.in);
            try{
                choice = scanner.nextInt();
                break;
            } catch (Exception exception) {
                System.out.println("Ошибка ввода. Введите цифру.");
            }
        }
        return choice;
    }
}
