package org.example.comics;

public class FabricMenu implements ShowMenu{
    @Override
    public String showMenuOptions() {
        String str =    "1. Создать комикс" +
                        "\n2. Удалить комикс из фабрики" +
                        "\n3. Показать список комиксов" +
                        "\n4. Редактировать параметры комикса" +
                        "\n0. Выход";
        return str;
    }
}
