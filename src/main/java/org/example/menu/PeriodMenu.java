package org.example.menu;

import org.example.model.User;

public class PeriodMenu implements ShowMenu {
    @Override
    public String showMenuOptions(User user) {
        String str = "";
        str += "1. День\n2. Месяц\n3. Год\n0. Выход";
        return str;
    }
}
