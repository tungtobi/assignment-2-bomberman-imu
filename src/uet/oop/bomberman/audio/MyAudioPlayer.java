package uet.oop.bomberman.audio;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static uet.oop.bomberman.audio.MyAudioPlayer.Loopable.NONELOOP;

public class MyAudioPlayer implements Runnable {

    // Tên file các audio
    public static final String BACKGROUND_MUSIC = "background_music";
    public static final String PLACE_BOMB = "place_bomb";
    public static final String POWER_UP = "power_up";
    public static final String EXPLOSION = "explosion";
    public static final String DEAD = "dead";

    private Clip clip;

    public enum Loopable {
        NONELOOP,
        LOOP;
    }

    // Mặc định không phát lại
    private Loopable loopable = NONELOOP;

    public MyAudioPlayer(String fileName) {
        String path = "/audio/" + fileName + ".wav";

        try {
            URL defaultSound = getClass().getResource(path);
            AudioInputStream sound = AudioSystem.getAudioInputStream(defaultSound);
            // load the sound into memory (a Clip)
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }
    }

    public Loopable getLoopable() {
        return loopable;
    }

    public void setLoopable(Loopable loopable) {
        this.loopable = loopable;
    }

    public void play(){
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }

    @Override
    public void run() {
        switch (loopable) {
            case LOOP:
                this.loop();
                break;
            case NONELOOP:
                this.play();
                break;
        }
    }
}
