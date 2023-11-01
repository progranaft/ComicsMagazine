package org.example.comics;

import org.example.menu.*;
import org.example.model.Comics;
import org.example.model.User;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class ComicsShopLive {
    MainController mainController;
    public ComicsShopLive(MainController mainController){
        this.mainController = mainController;
    }

    public void go(){
        if (mainController.user.isAdministrator()) {
            this.goAdmin();
        } else {
            //this.goAdmin();
            this.goUser();
        }
    }

    public boolean deal(Shop shop, User user){
        boolean res = false;
        Comics comics = null;
        ComicsData comicsData = null;
        Integer count = null;
        while (true){
            System.out.println("Введите название приобретаемого комикса или \"отмена\" для выхода: ");
            Scanner scanner = new Scanner(System.in);
            String comicsName = scanner.nextLine();
            if (comicsName.equals("отмена")){
                return false;
            }
            for (Map.Entry<Comics, ComicsData> item:shop.comicsHashMap.entrySet()){
                if (item.getKey().getName().equals(comicsName)){
                    comics = item.getKey();
                    comicsData = item.getValue();
                }
            }
            if (comics != null && comicsData != null) {
                System.out.println("Комикс: " + comics.getName() + ". В наличие: " + comicsData.getQuantity() + " шт.");
                break;
            } else {
                System.out.println("Комикс не найден.");
            }
        }
        while(true){
            System.out.println("Введите количество приобретаемых комиксов или \"0\" для выхода: ");
            Integer quant = Inputs.inputInt();
            if (quant == 0) {
                return false;
            }
            if (quant <= comicsData.getQuantity()){
                count = quant;
                break;
            } else {
                System.out.println("В магазине нет столько комиксов");
            }
        }
        shop.sellComics(comics, user, count);
        return true;
    }

    public void goAdmin(){
        //Админская менюшка
        Menu menu = new Menu(mainController.user , new MainMenu());
        while (true) {
            Integer choice = menu.showMenu();
            if (choice == null) continue;
            if (choice == 1) { //Меню работы с магазином
                Menu magazineMenu = new Menu(mainController.user, new MagazineMenu());
                while (true){
                    Integer choice3 = magazineMenu.showMenu();
                    if (choice3 == null) continue;
                    if (choice3 == 1) { //Добавление комикса в магазин
                        System.out.println(this.addComicsInShop());
                    } else if (choice3 == 2) { //Удаление комиксса из магазина
                        Scanner scan = new Scanner(System.in);
                        System.out.println("Введите название удаляемого комикса");
                        String str = scan.nextLine();
                        mainController.shop.deleteComics(str);
                    } else if (choice3 == 3) { //Список всех комиксов в магазине
                        System.out.println(mainController.shop.showAllComics(mainController.user));
                    } else if (choice3 == 4) { //Поиск комиксов по магазину
                        System.out.println("Введите название/автора/жанр комикса");
                        Scanner input = new Scanner(System.in);
                        String str = input.nextLine();
                        System.out.println(mainController.shop.searchComics(str, mainController.user));
                        System.out.println("Для продолжения введите любой символ");
                        str = input.nextLine();
                        continue;
                    } else if (choice3 == 5) { //Список новых поступлений
                        System.out.println(mainController.shop.getPremierComics(mainController.user));
                    } else if (choice3 == 6) { //Список новинок
                        System.out.println(mainController.shop.getNewComics(mainController.user));
                    } else if (choice3 == 7) { //Купить комикс
                        if (this.deal(mainController.shop, mainController.user)){
                            System.out.println("Комикс приобретен");
                        }
                    } else if (choice3 == 0) { //Возврат на предыдущую старницу меню
                        break;
                    }
                }
            } else if (choice == 2) { // Меню базы комиксов
                Menu fabricMenu = new Menu(mainController.user, new FabricMenu());
                while (true) {
                    Integer choice2 = fabricMenu.showMenu();
                    if (choice2 == null) continue;
                    if (choice2 == 1) {
                        mainController.soundBuffer.getBackGround().stopPlay();
                        mainController.soundBuffer.getCreateComics().startPlay();
                        Comics comics = mainController.baseComics.createComics();
                        if (comics != null) {
                            Scanner in = new Scanner(System.in);
                            Menu addInMagazine = new Menu(mainController.user, (user)->"Добавить комикс в магазин?\n1. Да\n2. Нет");
                            Integer input = addInMagazine.showMenu();
                            if (input == 1) {
                                if (comics != null) {
                                    mainController.shop.addComics(comics);
                                }
                            }
                        }
                        mainController.soundBuffer.getCreateComics().stopPlay();
                        mainController.soundBuffer.getBackGround().startPlay();
                    } else if (choice2 == 2) {

                    } else if (choice2 == 3) {
                        System.out.println(mainController.baseComics.showComics());
                    } else if (choice2 == 4) { // Изменение комиксов
                        mainController.baseComics.changeComics();
                    } else if (choice2 == 0) {
                        break;
                    }
                }
            } else if (choice == 3) {

            } else if (choice == 4) { //Журнал
                Menu journalMenu = new Menu(mainController.user, new JournalMenu());
                while (true){
                    Integer choice4 = journalMenu.showMenu();
                    if (choice4 == null) continue;
                    if (choice4 == 1) {
                        System.out.println(mainController.shop.saleJournal.toString());
                    } else if (choice4 == 2) { //Топ комиксов
                        Menu period = new Menu(mainController.user, new PeriodMenu());
                        while (true) {
                            Integer choice5 = period.showMenu();
                            if (choice5 == null) continue;
                            if (choice5 == 1) { //За день
                                System.out.println(mainController.shop.getTopComics(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1)));
                            } else if (choice5 == 2) {//За месяц
                                System.out.println(mainController.shop.getTopComics(LocalDate.now().minusMonths(1), LocalDate.now().plusDays(1)));
                            } else if (choice5 == 3) {//За год
                                System.out.println(mainController.shop.getTopComics(LocalDate.now().minusYears(1), LocalDate.now().plusDays(1)));
                            } else if (choice5 == 0) {
                                break;
                            }
                        }
                    } else if (choice4 == 3) { // Топ авторов
                        Menu period = new Menu(mainController.user, new PeriodMenu());
                        while (true) {
                            Integer choice5 = period.showMenu();
                            if (choice5 == null) continue;
                            if (choice5 == 1) { //За день
                                System.out.println(mainController.shop.getTopAuthors(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1)));
                            } else if (choice5 == 2) {//За месяц
                                System.out.println(mainController.shop.getTopAuthors(LocalDate.now().minusMonths(1), LocalDate.now().plusDays(1)));
                            } else if (choice5 == 3) {//За год
                                System.out.println(mainController.shop.getTopAuthors(LocalDate.now().minusYears(1), LocalDate.now().plusDays(1)));
                            } else if (choice5 == 0) {
                                break;
                            }
                        }
                    } else if (choice4 == 0) {
                        break;
                    }
                }
            } else if (choice == 5) {
                Menu actionsMenu = new Menu(mainController.user, new ActionsMenu());
                while (true){
                    Integer choice6 = actionsMenu.showMenu();
                    if (choice6 == null) continue;
                    if (choice6 == 1) { //Создать акцию
                        mainController.shop.createStockSale();
                    } else if (choice6 == 3) {//Просмотр списска акций
                        System.out.println(mainController.shop.showStockSales());
                    } else if (choice6 == 4) {//Добавить комикс в акцию

                    } else if (choice6 == 0) {
                        break;
                    }
                }
            } else if (choice == 6) {

            } else if (choice == 9) {
                mainController.save();
            } else if (choice == 0) {
                Menu exitMenu = new Menu(mainController.user ,(user)->"Сохранить?\n1. Да\n2. Нет");
                Integer input = exitMenu.showMenu();
                if (input == null) continue;
                if (input == 1) {
                    mainController.save();
                    break;
                } if (input == 2){
                    break;
                } else {
                    continue;
                }
            } else if (choice == 123) {
                this.addTestComics();
            } else {
                continue;
            }
        }
    }

    private String addComicsInShop(){
        String res = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите название комикса:");
        String str = scan.nextLine();
        Comics cms = mainController.baseComics.checkComics(str);
        if (cms != null) {
            mainController.shop.addComics(cms);
            res = "Комикс добавлен в магазин";
        } else {
            res = "Комикс не найден, либо не существует";
        }
        return res;
    }

    public void goUser(){
        Menu menu = new Menu(mainController.user , new MainMenu());
        while (true) {
            Integer choice = menu.showMenu();
            if (choice == 1) { //Меню работы с магазином
                Menu magazineMenu = new Menu(mainController.user, new MagazineMenu());
                while (true){
                    Integer choice3 = magazineMenu.showMenu();
                    if (choice3 == 1) { //Список всех комиксов в магазине
                        System.out.println(mainController.shop.showAllComics(mainController.user));
                    } else if (choice3 == 2) { //Поиск комиксов по магазину
                        System.out.println("Введите название/автора/жанр комикса");
                        Scanner input = new Scanner(System.in);
                        String str = input.nextLine();
                        System.out.println(mainController.shop.searchComics(str, mainController.user));
                        System.out.println("Для продолжения введите любой символ");
                        str = input.nextLine();
                        continue;
                    } else if (choice3 == 3) { //Список новых поступлений
                        System.out.println(mainController.shop.getPremierComics(mainController.user));
                    } else if (choice3 == 4) { //Список новинок
                        System.out.println(mainController.shop.getNewComics(mainController.user));
                    } else if (choice3 == 5 ) { // Купить комикс



                    } else if (choice3 == 0) { //Возврат на предыдущую старницу меню
                        break;
                    }
                }
            } else if (choice == 2) {



            } else if (choice == 3) {



            } else if (choice == 4) {



            } else if (choice == 5) {



            } else if (choice == 6) {



            } else if (choice == 9) {
                mainController.save();
            } else if (choice == 0) {
                Menu exitMenu = new Menu(mainController.user ,(user)->"Сохранить?\n1. Да\n2. Нет");
                Integer input = exitMenu.showMenu();
                if (input == 1) {
                    mainController.save();
                    break;
                } if (input == 2){
                    break;
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
    }

    public void addTestComics(){
        mainController.shop.comicsHashMap.put(mainController.baseComics.getSpiderMan(), new ComicsData(9, LocalDate.of(2022, 10, 1), 12.0, 34.0, 12345L));
        mainController.shop.comicsHashMap.put(mainController.baseComics.getAvengers2(), new ComicsData(7, LocalDate.of(2023, 5, 23), 10.0, 53.0, 1234523L));
    }

}
