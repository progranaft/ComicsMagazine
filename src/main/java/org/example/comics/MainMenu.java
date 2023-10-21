package org.example.comics;

public class MainMenu implements ShowMenu{
    @Override
    public String showMenuOptions() {
        String str =    "1. Добавить комикс" +
                        "\n2. Удалить комикс" +
                        "\n3. Показать список комиксов" +
                       "\n4. Сохранить" +
                        "\n5. База комиксов" +
                        "\n6. Поиск комиксов по магазину" +
                        "\n0. Выход";
        return str;
    }
}
