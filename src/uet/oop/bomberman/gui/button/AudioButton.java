package uet.oop.bomberman.gui.button;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.audio.MyAudioPlayer;
import uet.oop.bomberman.gui.Launcher;

import java.awt.event.ActionEvent;

public class AudioButton extends Button {
    public AudioButton(Launcher game) {
        super(game, "Audio: " + (!MyAudioPlayer.isMuted()? "On" : "Off"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyAudioPlayer.mute();
        onClickSound();
        updateText();
    }

    private void updateText() {
        setText("Audio: " + (!MyAudioPlayer.isMuted()? "On" : "Off"));
    }
}
