package org.example.comics;

public class JournalMenu implements ShowMenu{
    @Override
    public String showMenuOptions(User user) {
        String str = "";
        if (user.isAdministrator()) {
            str += "\n1. Просмотр журнала" +
                    "\n2. Топ продаж" +
                    "\n3. Тов авторов" +
                    "\n4. " +
                    "\n5. " +
                    "\n6. " +
                    "\n7. " +
                    "\n0. Выход";
        } else {

        }
        return str;
    }
}