package org.example.comics;

import org.example.journal.SaleJournal;
import org.example.journal.SaleRecord;
import org.example.journal.TopAuthor;
import org.example.journal.TopComics;
import org.example.model.ComicsData;
import org.example.stock.Stock;
import org.example.stock.StockSale;
import org.example.model.Comics;
import org.example.model.ComicsLegacy;
import org.example.model.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Shop implements Serializable {
    protected HashMap<Comics, ComicsData> comicsHashMap;
    protected HashSet<User> users;
    protected SaleJournal saleJournal;
    protected Stock stock;
    protected Double money;
    public Shop(){
        this.comicsHashMap = new HashMap<>();
        this.stock = new Stock();
        this.users = new HashSet<>();
        this.saleJournal = new SaleJournal();
        this.money = 0.;
    }

    public int addComicsInStock(String comicsName, String stockSaleName) {
        Map.Entry<Comics, ComicsData> comics = getComics(comicsName);
        StockSale stockSale = stock.getStockSale(stockSaleName);
        if (comics != null && stockSale != null) {
            comics.getValue().setStockSale(stockSale);
            return 0;
        } else {
            return -1;
        }
    }

    public Map.Entry<Comics, ComicsData> getComics(String name){
        Map.Entry<Comics, ComicsData> res = null;
        for (Map.Entry<Comics, ComicsData> entry:this.comicsHashMap.entrySet()) {
            if (entry.getKey().getName().equals(name)){
                res = entry;
            }
        }
        return res;
    }

    public boolean sellComics(Comics comics, User user, Integer quantity){
        boolean res = false;
        if (this.comicsHashMap.get(comics).getQuantity() > quantity) {
            this.comicsHashMap.get(comics).setQuantity(this.comicsHashMap.get(comics).getQuantity() - quantity);
            this.money += this.comicsHashMap.get(comics).transactionPrice()*quantity;
            this.saleJournal.addSaleRecords(comics, user, quantity, this.comicsHashMap.get(comics).transactionPrice()*quantity);
            res = true;
        } else if (this.comicsHashMap.get(comics).getQuantity() == quantity) {
            this.money += this.comicsHashMap.get(comics).transactionPrice()*quantity;
            this.saleJournal.addSaleRecords(comics, user, quantity, this.comicsHashMap.get(comics).transactionPrice()*quantity);
            this.comicsHashMap.remove(comics);
            res = true;
        }
        return res;
    }

    public String getTopComics(LocalDate dateStart, LocalDate dateEnd){
        ArrayList<TopComics> tmp = new ArrayList<>();
        for (SaleRecord saleRecord:saleJournal.getSaleRecords()){
            if (saleRecord.getSaleDate().isAfter(dateStart) && saleRecord.getSaleDate().isBefore(dateEnd)){
                Integer index = null;
                for (int i = 0; i < tmp.size(); i++){
                    if (saleRecord.getComics().getName().equals(tmp.get(i).getName())){
                        index = i;
                    }
                }
                if (index != null) {
                    tmp.get(index).setQuantity(tmp.get(index).getQuantity() + saleRecord.getQuantity());
                } else {
                    tmp.add(new TopComics(saleRecord.getComics().getName(), saleRecord.getQuantity()));
                }
            }
        }
        tmp.sort(new Comparator<TopComics>() {
            @Override
            public int compare(TopComics o1, TopComics o2) {
                return o2.getQuantity()-o1.getQuantity();
            }
        });
        StringBuilder res = new StringBuilder();
        res.append(dateStart).append(" ").append(dateEnd).append("\n");
        res.append(String.format("%20s %6s\n", "Комикс", "Кол-во"));
        for (TopComics topComics:tmp){
            res.append(String.format("%20s %6s\n", topComics.getName(), topComics.getQuantity()));
        }
        return res.toString();
    }

    public String getTopAuthors(LocalDate dateStart, LocalDate dateEnd){
        ArrayList<TopAuthor> topTopAuthors = new ArrayList<>();
        for (SaleRecord saleRecord: saleJournal.getSaleRecords()){
            if (saleRecord.getSaleDate().isAfter(dateStart) && saleRecord.getSaleDate().isBefore(dateEnd)){
                Integer index = null;
                for (int i = 0; i < topTopAuthors.size(); i++){
                    if (saleRecord.getComics().getAuthor().equals(topTopAuthors.get(i).getName())){
                        index = i;
                    }
                }
                if (index != null) {
                    topTopAuthors.get(index).setCountComics(topTopAuthors.get(index).getCountComics() + saleRecord.getQuantity());
                } else {
                    topTopAuthors.add(new TopAuthor(saleRecord.getComics().getAuthor(), saleRecord.getQuantity()));
                }
            }
        }
        topTopAuthors.sort(new Comparator<TopAuthor>() {
            @Override
            public int compare(TopAuthor o1, TopAuthor o2) {
                return o2.getCountComics()-o1.getCountComics();
            }
        });
        StringBuilder res = new StringBuilder();
        res.append(String.format("%20s %6s\n", "Автор", "Кол-во"));
        for (TopAuthor topAuthor : topTopAuthors){
            res.append(String.format("%20s %6s\n", topAuthor.getName(), topAuthor.getCountComics()));
        }
        return res.toString();
    }

    public String showUsersSale(User user){
        ArrayList<SaleRecord> saleRecords = this.saleJournal.getUsersSale(user);
        StringBuilder res = new StringBuilder();
        for (SaleRecord saleRecord : saleRecords){
            res.append(saleRecord.toString()).append("\n");
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
            res.append(String.format("| %20s | %10s | %10s | %10s | %15s | %5s | %15s | %8s | %10s | %7s | %6s | %5s | %10s | %20s |\n",
                    "Название", "Жанр", "Дт. созд.", "FabricID", "Издательство", "Стр.", "Дизайнер", "Автор", "Дт. завоза", "MgzId", "Кол-во", "Закуп", "Цена/Акция", "Предистория"));
            res.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            for(Map.Entry<Comics, ComicsData> comic:comicsHashMap.entrySet()){
                Comics tmp = comic.getKey();
                ComicsData tmp2 = comic.getValue();
                StringBuilder resPrice = new StringBuilder();
                if (tmp2.getStockSale() != null && LocalDate.now().isAfter(tmp2.getStockSale().getStartActions()) && LocalDate.now().isBefore(tmp2.getStockSale().getEndActions())) {
                    //Double result = tmp2.getSalePrice()*(1-tmp2.stockSale.getDiscount());
                    resPrice.append(tmp2.getSalePrice()).append("/");
                    resPrice.append(String.format("%.2f", tmp2.getStockSalePrice()));
                } else {
                    resPrice.append(tmp2.getSalePrice());
                }
                res.append(String.format("| %20s | %10s | %10s | %10s | %15s | %5s | %15s | %8s | %10s | %7s | %6s | %5s | %10s |",
                        tmp.getName(), tmp.getGenre(), tmp.getPublicationDate().toString(),
                        tmp.getFabricId(), tmp.getPublishingHouse(), tmp.getPages(), tmp.getDesigner(), tmp.getAuthor(),
                        tmp2.getDataReceipts(), tmp2.getProductId(), tmp2.getQuantity(), tmp2.getCoastPrice(), resPrice));
                if (tmp instanceof ComicsLegacy) {
                    ComicsLegacy tmpLeg = (ComicsLegacy) tmp;
                    res.append(String.format(" %20s |\n", tmpLeg.getLegacy().getName()));
                } else {
                    res.append(String.format(" %20s |\n", "Нет"));
                }
            }
            res.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        } else {
            res.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            res.append(String.format("| %20s | %10s | %10s | %15s | %5s | %15s | %8s | %10s | %7s | %6s | %10s | %20s |\n",
                    "Название", "Жанр", "Дт. созд.", "Издательство", "Стр.", "Дизайнер", "Автор", "Дт. завоза", "MgzId", "Кол-во", "Цена/Акция", "Предистория"));
            res.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            for(Map.Entry<Comics, ComicsData> comic:comicsHashMap.entrySet()){
                Comics tmp = comic.getKey();
                ComicsData tmp2 = comic.getValue();
                StringBuilder resPrice = new StringBuilder();
                if (tmp2.getStockSale() != null && LocalDate.now().isAfter(tmp2.getStockSale().getStartActions()) && LocalDate.now().isBefore(tmp2.getStockSale().getEndActions())) {
                    //Double result = tmp2.getSalePrice()*(1-tmp2.stockSale.getDiscount());
                    resPrice.append(tmp2.getSalePrice()).append("/");
                    resPrice.append(String.format("%.2f", tmp2.getStockSalePrice()));
                } else {
                    resPrice.append(tmp2.getSalePrice());
                }
                res.append(String.format("| %20s | %10s | %10s | %15s | %5s | %15s | %8s | %10s | %7s | %6s | %10s |",
                        tmp.getName(), tmp.getGenre(), tmp.getPublicationDate().toString(),
                        tmp.getPublishingHouse(), tmp.getPages(), tmp.getDesigner(), tmp.getAuthor(),
                        tmp2.getDataReceipts(), tmp2.getProductId(), tmp2.getQuantity(), resPrice));
                if (tmp instanceof ComicsLegacy) {
                    ComicsLegacy tmpLeg = (ComicsLegacy) tmp;
                    res.append(String.format(" %20s |\n", tmpLeg.getLegacy().getName()));
                } else {
                    res.append(String.format(" %20s |\n", "Нет"));
                }
            }
            res.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
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

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
