package org.example.comics;

public class FabricMenu extends MainMenu{
    protected String options;
    public FabricMenu(){
        this.options = String.format(   "1. Создать комикс" +
                                        "\n2. Удалить комикс из редакции" +
                                        "\n3. Показать список комиксов" +
                                        "\n0. Выход");
    }
    @Override
    public String toString() {
        return this.options;
    }
}
