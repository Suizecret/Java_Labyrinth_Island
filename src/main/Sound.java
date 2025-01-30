package main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/sounds/backGroundMusic.wav");
        soundURL[1] = getClass().getResource("/sounds/collect.wav");
        soundURL[2] = getClass().getResource("/sounds/door.wav");
        soundURL[3] = getClass().getResource("/sounds/levelUP.wav");
    }

    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void play(){
        clip.start();

    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
