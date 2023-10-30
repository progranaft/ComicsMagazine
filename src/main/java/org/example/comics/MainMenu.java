package org.example.comics;

public class MainMenu implements ShowMenu{
    @Override
    public String showMenuOptions(User user) {
        String str = "";
        if (user.isAdministrator()) {
            if (Inputs.pegs) str += Inputs.funky();
            str += "\n1. Магазин комиксов" +
                    "\n2. База комиксов" +
                    "\n3. База покупателей" +
                    "\n4. " +
                    "\n5. " +
                    "\n6. " +
                    "\n9. Сохранить" +
                    "\n0. Выход";
        } else {
            str += "\n1. Магазин комиксов" +
                    "\n2. Мои покупки" +
                    "\n3. Настройки" +
                    "\n4. " +
                    "\n5. " +
                    "\n6. " +
                    "\n9. Сохранить" +
                    "\n0. Выход";
        }
        return str;
    }
}
