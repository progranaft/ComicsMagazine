package org.example;

import org.example.comics.*;
import org.example.sounds.Sound;
import org.example.sounds.SoundBuffer;

public class Main {
    public static void main(String[] args) {

        SoundBuffer soundBuffer = new SoundBuffer();
        soundBuffer.setDaemon(true);
        soundBuffer.getBackGround().startPlay();
        MainController mainController = new MainController(soundBuffer);
        mainController.start();

    }
}