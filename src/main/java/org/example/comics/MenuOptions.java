package org.example.comics;

public class MenuOptions {
    protected String options;
    public MenuOptions(){
        this.options = String.format(   "1. Добавить комикс" +
                                        "\n2. Удалить комикс" +
                                        "\n3. Показать список комиксов");
    }
    @Override
    public String toString() {
        return this.options;
    }
}
