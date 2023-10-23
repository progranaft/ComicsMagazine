package org.example.comics;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Inputs {
    public static Integer inputInt(){
        Integer number = null;
        while(true){
            Scanner in = new Scanner(System.in);
            try {
                number = in.nextInt();
                break;
            } catch (Exception ex) {
                System.out.println("Ошибка ввода, введите число!");
            }
        }
        return number;
    }

    public static Double inputDouble(){
        Double number = null;
        while(true){
            Scanner in = new Scanner(System.in);
            try {
                number = in.nextDouble();
                break;
            } catch (Exception ex) {
                System.out.println("Ошибка ввода, введите число или число с точкой!");
            }
        }
        return number;
    }

    public static Long inputLong(){
        Long number = null;
        while(true){
            Scanner in = new Scanner(System.in);
            try {
                number = in.nextLong();
                break;
            } catch (Exception ex) {
                System.out.println("Ошибка ввода, введите число!");
            }
        }
        return number;
    }

    public static LocalDate inputDate(){
        LocalDate date = null;
        while(true){
            Scanner in = new Scanner(System.in);
            String str = in.nextLine();
            try {
                date = LocalDate.parse(str);
                break;
            } catch (Exception ex) {
                System.out.println("Ошибка формата даты, правильный формат - (ГГГГ-ММ-ДД)");
            }
        }
        return date;
    }
}
