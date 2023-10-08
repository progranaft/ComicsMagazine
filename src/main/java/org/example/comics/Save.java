package org.example.comics;

import java.io.Serializable;

public class Save implements Serializable {
    private FabricComics fc;
    private Shop shop;
    public Save(FabricComics fc, Shop shop) {
        this.fc = fc;
        this.shop = shop;
    }

    public FabricComics getFc() {
        return fc;
    }

    public void setFc(FabricComics fc) {
        this.fc = fc;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
