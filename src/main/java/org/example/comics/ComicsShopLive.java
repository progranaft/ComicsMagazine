package org.example.comics;

public class ComicsShopLive {
    public void start(){
        Shop shop = new Shop();
        shop.addComics(FabricComics.getAvengers());
        shop.addComics(FabricComics.getAvengers2());
        shop.addComics(FabricComics.getSpiderMan());

        Menu menu = new Menu(new MenuOptions());

        while (true) {
            Integer choice = menu.showMenu();
            if (choice == 1) {
                shop.createAndAddComics();
            } else if (choice == 3) {
                System.out.println(shop.showComicsList());
            } else {
                continue;
            }
        }
    }
}
