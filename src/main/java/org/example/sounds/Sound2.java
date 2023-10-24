package org.example.sounds;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound2 extends Thread {
    Clip clip;
    @Override
    public void run() {
        try {
            File soundFile = new File("search.wav"); //Звуковой файл

            //Получаем AudioInputStream
            //Вот тут могут полететь IOException и UnsupportedAudioFileException
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);

            //Получаем реализацию интерфейса Clip
            //Может выкинуть LineUnavailableException
            clip = AudioSystem.getClip();

            //Загружаем наш звуковой поток в Clip
            //Может выкинуть IOException и LineUnavailableException
            clip.open(ais);
            //while (true) {
                clip.setFramePosition(0); //устанавливаем указатель на старт
                clip.start(); //Поехали!!!
                //Thread.sleep(clip.getMicrosecondLength()/1000);
                //clip.stop(); //Останавливаем
                //clip.close(); //Закрываем
            //}


            //Если не запущено других потоков, то стоит подождать, пока клип не закончится
            //В GUI-приложениях следующие 3 строчки не понадобятся


        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
        }
    }
    public Clip getClip() {
        return clip;
    }
    public void setClip(Clip clip) {
        this.clip = clip;
    }
}
