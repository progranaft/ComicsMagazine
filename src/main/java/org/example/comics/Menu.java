package org.example.comics;

import java.util.Scanner;

public class Menu {
    private ShowMenu showMenu;
    public Menu(ShowMenu options){
        this.showMenu = options;
    }
    public Integer showMenu(){
        Integer choice = null;
        System.out.println(showMenu.showMenuOptions());
        choice = Inputs.inputInt();
//        while (true) {
//            System.out.println(showMenu.showMenuOptions());
//            Scanner scanner = new Scanner(System.in);
//            try{
//                choice = scanner.nextInt();
//                break;
//            } catch (Exception exception) {
//                System.out.println("Ошибка ввода. Введите цифру.");
//            }
//        }
        return choice;
    }
}
