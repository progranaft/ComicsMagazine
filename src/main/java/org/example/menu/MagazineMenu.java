package org.example.menu;

import org.example.comics.Inputs;
import org.example.model.User;

public class MagazineMenu implements ShowMenu {
    @Override
    public String showMenuOptions(User user) {
        String str = "";
        if (user.isAdministrator()) {
            if (Inputs.pegs) str += Inputs.funky();
            str += "\n1. Добавить комикс" +
                    "\n2. Удалить комикс" +
                    "\n3. Показать список комиксов в магазине" +
                    "\n4. Поиск комиксов по магазину" +
                    "\n5. Поступления в этом месяце" +
                    "\n6. Новинки в этом месяце" +
                    "\n7. Купить комикс" +
                    "\n0. Выход";
        } else {
            str += "\n1. Показать список комиксов в магазине" +
                    "\n2. Поиск комиксов по магазину" +
                    "\n3. Поступления в этом месяце" +
                    "\n4. Новинки в этом месяце" +
                    "\n5. Купить комикс" +
                    "\n6. " +
                    "\n0. Выход";
        }

        return str;
    }
}
