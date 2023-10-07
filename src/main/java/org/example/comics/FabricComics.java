package org.example.comics;

import sun.util.resources.LocaleData;

import java.time.LocalDate;

public class FabricComics {
    public static Comics getAvengers(){
        return new Comics("Стэн Ли", "Джек Кирби", "Avengers", LocalDate.of(1963, 9, 1), 150, "Marvel Comics", "Fantastic", 10, 30);
    }
    public static ComicsLegacy getAvengers2(){
        return new ComicsLegacy("Стэн Ли", "Джек Кирби", "Avengers 2", LocalDate.of(1970, 3, 22), 175, "Marvel Comics", "Fantastic", 10, 35, getAvengers());
    }
    public static Comics getSpiderMan(){
        return new Comics("Стэн Ли", "Стив Дитко", "Spider-Man", LocalDate.of(1962, 7, 15), 278, "Marvel Comics", "Fantastic", 10, 40);
    }
}
