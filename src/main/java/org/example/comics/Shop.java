package org.example.comics;

import javafx.scene.shape.ClosePathBuilder;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

public class Shop implements Serializable {
    protected HashMap<Comics, ComicsData> comicsHashMap;
    protected HashSet<User> users;
    protected SaleJournal saleJournal;
    protected Double money;
    public Shop(){
        this.comicsHashMap = new HashMap<>();
        this.users = new HashSet<>();
        this.saleJournal = new SaleJournal();
        this.money = 0.;
    }

    public boolean sellComics(Comics comics, User user, Integer quantity){
        boolean res = false;
        if (this.comicsHashMap.get(comics).getQuantity() > quantity) {
            this.comicsHashMap.get(comics).setQuantity(this.comicsHashMap.get(comics).getQuantity() - quantity);
            this.money += this.comicsHashMap.get(comics).getSalePrice()*quantity;
            this.saleJournal.addSaleRecords(comics, user, quantity, this.comicsHashMap.get(comics).getSalePrice());
            res = true;
        } else if (this.comicsHashMap.get(comics).getQuantity() == quantity) {
            this.money += this.comicsHashMap.get(comics).getSalePrice()*quantity;
            this.saleJournal.addSaleRecords(comics, user, quantity, this.comicsHashMap.get(comics).getSalePrice());
            this.comicsHashMap.remove(comics);
            res = true;
        }
        return res;
    }

    public String getTopComics(){
        ArrayList<SaleRecord> tmp = new ArrayList<>();
        for (SaleRecord saleRecord: saleJournal.getSaleRecords()){
            boolean contains = false;
            for (int i = 0; i < tmp.size(); i++) {
                if (saleRecord.getComics().getName().equals(tmp.get(i).getComics().getName())) {
                    contains = true;
                }
            }
            if (contains) {
                for (SaleRecord saleRecord1:tmp){
                    if (saleRecord1.getComics().getName().equals(saleRecord.getComics().getName())) {
                        saleRecord1.setQuantity(saleRecord1.getQuantity()+saleRecord.getQuantity());
                    }
                }
            } else {
                SaleRecord slrc = new SaleRecord();
                slrc.setComics(saleRecord.getComics());
                slrc.setQuantity(saleRecord.getQuantity());
                tmp.add(slrc);
            }
        }
        tmp.sort(new Comparator<SaleRecord>() {
            @Override
            public int compare(SaleRecord o1, SaleRecord o2) {
                return o2.getQuantity()-o1.getQuantity();
            }
        });
        StringBuilder res = new StringBuilder();
        res.append(String.format("%20s %6s\n", "Название", "Кол-во"));
        for(SaleRecord saleRecord:tmp){
            res.append(String.format("%20s %6s\n" ,saleRecord.getComics().getName(), saleRecord.getQuantity()));
        }
        return res.toString();
    }

    public String getTopAuthors(){
        ArrayList<Author> topAuthors = new ArrayList<>();
        for (SaleRecord saleRecord: saleJournal.getSaleRecords()){
            boolean contains = false;
            for (Author author:topAuthors){
                if (saleRecord.getComics().getAuthor().equals(author.getName())){
                    contains = true;
                }
            }
            if (contains) {
                for (Author author:topAuthors){
                    if (saleRecord.getComics().getAuthor().equals(author.getName())){
                        author.setCountComics(author.getCountComics() + saleRecord.getQuantity());
                    }
                }
            } else {
                topAuthors.add(new Author(saleRecord.getComics().getAuthor(), saleRecord.getQuantity()));
            }
        }
        topAuthors.sort(new Comparator<Author>() {
            @Override
            public int compare(Author o1, Author o2) {
                return o2.getCountComics()-o1.getCountComics();
            }
        });
        StringBuilder res = new StringBuilder();
        res.append(String.format("%20s %6s\n", "Автор", "Кол-во"));
        for (Author author:topAuthors){
            res.append(String.format("%20s %6s\n", author.getName(), author.getCountComics()));
        }
        return res.toString();
    }

    public void addComics(Comics comics){
        if (comicsHashMap.containsKey(comics)) {
            Integer tmp = comicsHashMap.get(comics).getQuantity();
            Scanner input = new Scanner(System.in);
            tmp += input.nextInt();
            comicsHashMap.get(comics).setQuantity(tmp);
        } else {
            System.out.println("Введите цену закупки: ");
            Double coastPrice = Inputs.inputDouble();
            System.out.println("введите цену продажи: ");
            Double price = Inputs.inputDouble();
            System.out.println("Введите ID продукта: ");
            Long id = Inputs.inputLong();
            System.out.println("Введите количество: ");
            Integer quan = Inputs.inputInt();
            comicsHashMap.put(comics, new ComicsData(quan ,LocalDate.now(), coastPrice, price, id));
        }
    }

    public String showUsersList(){
        StringBuilder str = new StringBuilder();
        for (User user : this.users) {
            str.append(user.toString()).append("\n");
        }
        return str.toString();
    }

    private String showComicsList(HashMap<Comics, ComicsData> comicsHashMap, User user) {
        StringBuilder res = new StringBuilder();
        if (user.isAdministrator()) {
            res.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            res.append(String.format("| %20s | %10s | %10s | %10s | %15s | %5s | %15s | %8s | %10s | %7s | %6s | %5s | %5s | %20s |\n",
                    "Название", "Жанр", "Дт. созд.", "FabricID", "Издательство", "Стр.", "Дизайнер", "Автор", "Дт. завоза", "MgzId", "Кол-во", "Закуп", "Цена", "Предистория"));
            res.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            for(Map.Entry<Comics, ComicsData> comic:comicsHashMap.entrySet()){
                Comics tmp = comic.getKey();
                ComicsData tmp2 = comic.getValue();
                res.append(String.format("| %20s | %10s | %10s | %10s | %15s | %5s | %15s | %8s | %10s | %7s | %6s | %5s | %5s |",
                        tmp.getName(), tmp.getGenre(), tmp.getPublicationDate().toString(),
                        tmp.getFabricId(), tmp.getPublishingHouse(), tmp.getPages(), tmp.getDesigner(), tmp.getAuthor(),
                        tmp2.getDataReceipts(), tmp2.getProductId(), tmp2.getQuantity(), tmp2.getCoastPrice(), tmp2.getSalePrice()));
                if (tmp instanceof ComicsLegacy) {
                    ComicsLegacy tmpLeg = (ComicsLegacy) tmp;
                    res.append(String.format(" %20s |\n", tmpLeg.getLegacy().getName()));
                } else {
                    res.append(String.format(" %20s |\n", "Нет"));
                }
            }
            res.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        } else {
            res.append(String.format("| %20s | %10s | %10s | %15s | %5s | %15s | %8s | %10s | %7s | %6s | %5s | %15s |\n",
                    "Название", "Жанр", "Дт. созд.", "Издательство", "Стр.", "Дизайнер", "Автор", "Дт. завоза", "MgzId", "Кол-во", "Цена", "Предистория"));
            res.append("-------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            for(Map.Entry<Comics, ComicsData> comic:this.comicsHashMap.entrySet()){
                Comics tmp = comic.getKey();
                ComicsData tmp2 = comic.getValue();
                res.append(String.format("| %20s | %10s | %10s | %12s | %5s | %8s | %8s | %10s | %7s | %6s | %5s |",
                        tmp.getName(), tmp.getGenre(), tmp.getPublicationDate().toString(),
                        tmp.getPublishingHouse(), tmp.getPages(), tmp.getDesigner(), tmp.getAuthor(),
                        tmp2.getDataReceipts(), tmp2.getProductId(), tmp2.getQuantity(), tmp2.getCoastPrice(), tmp2.getSalePrice()));
                if (tmp instanceof ComicsLegacy) {
                    ComicsLegacy tmpLeg = (ComicsLegacy) tmp;
                    res.append(String.format(" %20s |\n", tmpLeg.getLegacy().getName()));
                } else {
                    res.append(String.format(" %20s |\n", "Нет"));
                }
            }
        }
        return res.toString();
    }

    public String showAllComics(User user){
        String res = "";
        res = this.showComicsList(this.comicsHashMap, user);
        return res;
    }

    public void deleteComics(String name) {
        Comics comics = null;
        for (Map.Entry<Comics, ComicsData> comic : comicsHashMap.entrySet()) {
            if (comic.getKey().getName().equals(name)) {
                comics = comic.getKey();
                break;
            }
        }
        if (comics != null) {
            if (comicsHashMap.get(comics).getQuantity() > 1) {
                Integer tmp = comicsHashMap.get(comics).getQuantity();
                tmp--;
                comicsHashMap.get(comics).setQuantity(tmp);
            } else {
                comicsHashMap.remove(comics);
            }
            System.out.println("Комикс успешно удален");
        } else {
            System.out.println("Комикс не найден");
        }
    }

    public HashSet<User> getUsers() {
        return users;
    }

    public void setUsers(HashSet<User> users) {
        this.users = users;
    }

    public HashMap<Comics, ComicsData> searchComicsName(String name){
        HashMap<Comics, ComicsData> comicsList = new HashMap<>();
        for (Map.Entry<Comics, ComicsData> comics : this.comicsHashMap.entrySet()){
            if (comics.getKey().getName().contains(name)) {
                comicsList.put(comics.getKey(), comics.getValue());
            }
        }
        return comicsList;
    }

    public HashMap<Comics, ComicsData> searchComicsAuthor(String author){
        HashMap<Comics, ComicsData> comicsList = new HashMap<>();
        for (Map.Entry<Comics, ComicsData> comics : this.comicsHashMap.entrySet()){
            if (comics.getKey().getAuthor().contains(author)) {
                comicsList.put(comics.getKey(), comics.getValue());
            }
        }
        return comicsList;
    }

    public HashMap<Comics, ComicsData> searchComicsGenre(String genre){
        HashMap<Comics, ComicsData> comicsList = new HashMap<>();
        for (Map.Entry<Comics, ComicsData> comics : this.comicsHashMap.entrySet()){
            if (comics.getKey().getGenre().contains(genre)) {
                comicsList.put(comics.getKey(), comics.getValue());
            }
        }
        return comicsList;
    }

    public String searchComics(String searchStr, User user) {
        HashMap<Comics, ComicsData> comicsList = new HashMap<>();
        comicsList.putAll(searchComicsName(searchStr));
        comicsList.putAll(searchComicsAuthor(searchStr));
        comicsList.putAll(searchComicsGenre(searchStr));
        return this.showComicsList(comicsList, user);
    }

    public String getPremierComics(User user){
        HashMap<Comics, ComicsData> tmp = new HashMap<>();
        for (Map.Entry<Comics, ComicsData> item : this.comicsHashMap.entrySet()) {
            if (item.getValue().getDataReceipts().isAfter(LocalDate.now().minusDays(30))) {
                tmp.put(item.getKey(), item.getValue());
            }
        }
        return this.showComicsList(tmp, user);
    }

    public String getNewComics(User user){
        HashMap<Comics, ComicsData> res = new HashMap<>();
        for (Map.Entry<Comics, ComicsData> item:this.comicsHashMap.entrySet()){
            if (item.getKey().getPublicationDate().isAfter(LocalDate.now().minusDays(30))) {
                res.put(item.getKey(), item.getValue());
            }
        }
        return this.showComicsList(res, user);
    }
}
