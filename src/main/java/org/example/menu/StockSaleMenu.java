package org.example.menu;

import org.example.model.User;

public class StockSaleMenu implements ShowMenu {
    @Override
    public String showMenuOptions(User user) {
        String str = "";
        str += "1. Создать акцию\n" +
                "2. Удалить акцию\n" +
                "3. Просмотр списска акций\n" +
                "4. Добавить комикс в акцию\n" +
                "0. Выход";
        return str;
    }
}
