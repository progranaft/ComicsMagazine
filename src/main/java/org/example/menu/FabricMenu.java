package org.example.menu;

import org.example.menu.ShowMenu;
import org.example.model.User;

public class FabricMenu implements ShowMenu {
    @Override
    public String showMenuOptions(User user) {
        String str =    "1. Создать новый комикс" +
                        "\n2. Удалить комикс из базы" +
                        "\n3. Показать список комиксов в базе" +
                        "\n4. Редактировать параметры комикса" +
                        "\n0. Выход";
        return str;
    }
}
