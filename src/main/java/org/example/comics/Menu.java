package org.example.comics;

import java.util.Scanner;

public class Menu {
    protected MainMenu mainMenu;
    public Menu(MainMenu options){
        this.mainMenu = options;
    }
    public Integer showMenu(){
        Integer choice = null;
        while (true) {
            System.out.println(this.mainMenu);
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
