package org.example.comics;

import java.util.Scanner;

public class Menu {
    private ShowMenu showMenu;
    private User user;
    public Menu(User user, ShowMenu showMenu){
        this.showMenu = showMenu;
        this.user = user;
    }
    public Integer showMenu(){
        Integer choice = null;
        System.out.println(showMenu.showMenuOptions(user));
        choice = Inputs.inputInt();
        return choice;
    }
}
