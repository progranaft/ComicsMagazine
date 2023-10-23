package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.comics.*;
import org.example.sounds.Sound;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Sound snd = new Sound();
        ComicsShopLive cms = new ComicsShopLive();
        cms.start();
        snd.start();
    }
}