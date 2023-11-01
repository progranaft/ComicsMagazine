package org.example.menu;

import org.example.comics.Inputs;
import org.example.model.User;

public class Menu {
    private ShowMenu shMenu;
    private User user;
    public Menu(User user, ShowMenu shMenu){
        this.shMenu = shMenu;
        this.user = user;
    }
    public Integer showMenu(){
        Integer choice = null;
        System.out.println(shMenu.showMenuOptions(user));
        choice = Inputs.inputInt();
        return choice;
    }
}
