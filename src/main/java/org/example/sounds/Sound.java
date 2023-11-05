package org.example.sounds;

import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sound {

    private Player player;
    private String path;

    public Sound(String path){
        this.path = path;
    }

    public void startPlay(){
        try {
            FileInputStream fis = new FileInputStream(path);
            player = new Player(fis);
        } catch (FileNotFoundException | javazoom.jl.decoder.JavaLayerException e) {
            e.printStackTrace();
        }
        new Thread() {
            public void run() {
                try {
                    player.play();
                } catch (Exception e) {
                    System.out.println("Error playing audio: " + e);
                }
            }
        }.start();
    }

    public void stopPlay(){
        if (player != null) {
            player.close();
        }
    }
}
