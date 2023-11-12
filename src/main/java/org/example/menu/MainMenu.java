package org.example.menu;

import org.example.comics.Inputs;
import org.example.model.User;

public class MainMenu implements ShowMenu {
    @Override
    public String showMenuOptions(User user) {
        String str = "";
        if (user.isAdministrator()) {
            if (Inputs.pegs) str += Inputs.funky();
            str += "\n1. Магазин комиксов" +
                    "\n2. База комиксов" +
                    "\n3. База покупателей" +
                    "\n4. Журнал покупок" +
                    "\n5. Настройка акций" +
                    "\n9. Сохранить" +
                    "\n0. Выход";
        } else {
            str += "\n1. Магазин комиксов" +
                    "\n2. Мои покупки" +
                    "\n3. Отложенные комиксы" +
                    "\n4. Настройки" +
                    "\n9. Сохранить" +
                    "\n0. Выход";
        }
        return str;
    }
}
