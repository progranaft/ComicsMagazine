package org.example.comics;

public class ChangeComicsMenu implements ShowMenu {
        @Override
    public String showMenuOptions(User user) {
        String str = "1. Изменить название" +
                "\n2. Изменить автора" +
                "\n3. Изменить дизайнера" +
                "\n4. Изменить дату издания" +
                "\n5. Изменить количество страниц" +
                "\n6. Изменить издательство" +
                "\n7. Изменить жанр" +
                "\n0. Выход";
        return str;
    }
}
