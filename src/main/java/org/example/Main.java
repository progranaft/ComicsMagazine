package org.example;

import org.example.comics.*;
import org.example.sounds.Sound;
import org.example.sounds.SoundBuffer;

public class Main {
    public static void main(String[] args) {

//        Sound backSound = new Sound("snd.mp3");
//        Sound createComics = new Sound("snd2.mp3");
        SoundBuffer soundBuffer = new SoundBuffer();
        soundBuffer.getBackGround().setDaemon(true);
        soundBuffer.getCreateComics().setDaemon(true);
        soundBuffer.getBackGround().startPlay();
        MainController mainController = new MainController(soundBuffer);
        mainController.start();

    }
}