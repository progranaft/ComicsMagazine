package org.example.comics;

public class MainMenu {
    protected String options;
    public MainMenu(){
        this.options = String.format(   "1. Добавить комикс" +
                                        "\n2. Удалить комикс" +
                                        "\n3. Показать список комиксов" +
                                        "\n4. Сохранить" +
                                        "\n5. Фабрика комиксов" +
                                        "\n0. Выход");
    }
    @Override
    public String toString() {
        return this.options;
    }
}
