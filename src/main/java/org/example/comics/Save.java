package org.example.comics;

import java.io.Serializable;

public class Save implements Serializable {
    private FabricComics fc;
    private Shop shop;
    //private Reception reception;

    public Save(){
        this.fc = new FabricComics();
        this.shop = new Shop();
        //this.reception = new Reception();
    }

//    public Reception getReception() {
//        return reception;
//    }
//    public void setReception(Reception reception) {
//        this.reception = reception;
//    }
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
