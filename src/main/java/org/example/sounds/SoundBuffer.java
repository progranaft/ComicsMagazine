package org.example.sounds;

public class SoundBuffer extends Thread {
    private Sound backGround;
    private Sound createComics;
    private Sound intro;

    public SoundBuffer () {
        this.backGround = new Sound("sounds\\snd.mp3");
        this.createComics = new Sound("sounds\\snd2.mp3");
        this.intro = new Sound("sounds\\info.mp3");
    }

    public Sound getIntro() {
        return intro;
    }

    public Sound getBackGround() {
        return backGround;
    }


    public Sound getCreateComics() {
        return createComics;
    }

}
