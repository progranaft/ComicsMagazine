package org.example.comics;

public class ActionsMenu implements ShowMenu{
    @Override
    public String showMenuOptions(User user) {
        String str = "";
        str += "1. Создать акцию\n" +
                "2. Удалить акцию\n" +
                "3. Просмотр списска акций\n" +
                "4. Добавить комикс в акцию" +
                "0. Выход";
        return str;
    }
}
