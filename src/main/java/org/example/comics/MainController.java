package org.example.comics;

import java.io.*;

public class MainController {
    protected User user;
    protected Reception reception;
    protected Shop shop;
    protected FabricComics fabricComics;
    protected Save save;

    public void start(){
        System.out.println(this.openSaveFile());
        this.reception = save.getReception();
        System.out.println(this.reception);
        this.shop = save.getShop();
        this.fabricComics = save.getFc();
        while (true) {
            this.user = this.reception.authorization();
            if (this.user == null) break;
            this.shop.users.add(this.user);
            ComicsShopLive csl = new ComicsShopLive(this);
            csl.go();
        }
    }

    public void save(){
        Save save = new Save();
        save.setReception(this.reception);
        save.setFc(this.fabricComics);
        save.setShop(this.shop);
        FileOutputStream fout = null;
        ObjectOutputStream oout = null;
        File file = new File("save.txt");
        if (!file.exists()){
            try {
                if( file.createNewFile() )
                    System.out.println("Файл сохранения создан");
                else
                    System.out.println("Something Wrong!");
            } catch (Exception ex) {
                System.out.println("Ошибика");
            }
        } else {
            System.out.println("Сохранение уже существует");
        }
        try {
            fout = new FileOutputStream(file);
            oout = new ObjectOutputStream(fout);
            oout.writeObject(save);
            System.out.println("Сохранение успешно перезаписано");
        } catch (FileNotFoundException ex) {
            System.out.println("Файл не найден");
        } catch (IOException ex) {
            System.out.println("Ошибка ввода вывода");
        } finally {
            try {
                oout.close();
            } catch (Exception ex) {
                System.out.println("Ошбика");
            }
        }
    }

    private String openSaveFile(){
        StringBuilder res = new StringBuilder();
        File saveFile = new File("save.txt");
        if (saveFile.exists()) {
            FileInputStream fin = null;
            ObjectInputStream oin = null;
            save = null;
            try {
                fin = new FileInputStream(saveFile);
                oin = new ObjectInputStream(fin);
                save = (Save) oin.readObject();
            } catch (Exception ex) {
                System.out.println("ошибка");
            } finally {
                try {
                    fin.close();
                } catch (IOException ex) {
                    System.out.println("шибка");
                }
            }
            res.append("Сохранение загружено");
        } else {
            save = new Save();
            res.append("Создан новый магазин");
        }
        return res.toString();
    }
}
