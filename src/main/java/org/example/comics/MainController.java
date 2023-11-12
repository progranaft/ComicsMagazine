package org.example.comics;

import org.example.model.User;
import org.example.sounds.SoundBuffer;

import java.io.*;

public class MainController {
    protected User user;
    protected Reception reception;
    protected Shop shop;
    protected BaseComics baseComics;
    protected Save save;
    protected SoundBuffer soundBuffer;

    public MainController(){}
    public MainController(SoundBuffer soundBuffer){
        this.soundBuffer = soundBuffer;
    }

    public void start(){
        System.out.println(this.openUsersFile());
        System.out.println(this.openSaveFile());
        System.out.println(this.reception);
        this.shop = save.getShop();
        this.baseComics = save.getFc();
        while (true) {
            this.user = this.reception.authorization(this);
            if (this.user == null) {
                soundBuffer.getBackGround().stopPlay();
                break;
            }
            this.shop.users.add(this.user);
            ComicsShopController csl = new ComicsShopController(this);
            csl.go();
        }
    }

    private String openUsersFile() {
        StringBuilder res = new StringBuilder();
        File usersFile = new File("users.txt");
        if (usersFile.exists()) {
            try (FileInputStream fin = new FileInputStream(usersFile);
                ObjectInputStream oin = new ObjectInputStream(fin);) {
                this.reception = (Reception) oin.readObject();
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("ошибка");
            }
            res.append("Список пользователей загружен");
        } else {
            this.reception = new Reception();
            res.append("Создан новый список пользователей");
        }
        return res.toString();
    }

    public String saveUsersFile() {
        StringBuilder res = new StringBuilder();
        File saveUsers = new File("users.txt");
        if (!saveUsers.exists()) {
            try {
                if (saveUsers.createNewFile()) {
                    res.append("Файл сохранения создан");
                } else {
                    res.append("Something Wrong!");
                }
            } catch (IOException e) {
                res.append("Ошибика");
            }
        } else {
            res.append("Сохранение уже существует");
        }
        try (FileOutputStream fout = new FileOutputStream(saveUsers);
             ObjectOutputStream oout = new ObjectOutputStream(fout);) {
            oout.writeObject(this.reception);
            res.append("Сохранение успешно перезаписано");
        } catch (IOException e) {
            res.append("Обшишббиикаа");
        }
        return res.toString();
    }

    public void save(){
        Save save = new Save();
        //save.setReception(this.reception);
        save.setFc(this.baseComics);
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
