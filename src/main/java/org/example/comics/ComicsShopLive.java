package org.example.comics;

import java.io.*;

public class ComicsShopLive {
    public void start(){
        Shop shop = null;
        FabricComics fc = null;
        File saveFile = new File("save.txt");
        if (saveFile.exists()) {
            FileInputStream fin = null;
            ObjectInputStream oin = null;
            Save save = null;
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
            shop = save.getShop();
            fc = save.getFc();
            System.out.println("Сохранение загружено");
        } else {
            shop = new Shop();
            fc = new FabricComics();
            System.out.println("Создан новый магазин");
        }


 //       FabricComics fc = new FabricComics();
        //       shop.addComics(fc.getAvengers());
//        shop.addComics(FabricComics.getAvengers2());
  //      shop.addComics(fc.getSpiderMan());

        Menu menu = new Menu(new MenuOptions());

        while (true) {
            Integer choice = menu.showMenu();
            if (choice == 1) {

                Comics comics = fc.createComics();
                if (comics != null) {
                    shop.addComics(comics);
                }
            } else if (choice == 3) {
                System.out.println(shop.showComicsList());
            } else if (choice == 4) {
                this.save(fc, shop);
            } else {
                continue;
            }
        }
    }

    public void save(FabricComics fc, Shop shop){
        Save save = new Save(fc, shop);
        FileOutputStream fout = null;
        ObjectOutputStream oout = null;
        File file = new File("save.txt");
        if (!file.exists()){
            try {
                if( file.createNewFile() )
                    System.out.println("File created!");
                else
                    System.out.println("Something Wrong!");
                } catch (Exception ex) {
                    System.out.println("Ошибика");
                }
        } else {
            System.out.println("File already exists!");
        }

        try {
            fout = new FileOutputStream(file);
            oout = new ObjectOutputStream(fout);
            oout.writeObject(save);
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
}
