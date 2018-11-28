package uet.oop.bomberman.gui.button;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.gui.Launcher;

import java.awt.event.ActionEvent;

public class PlayButton extends Button {
    public PlayButton(Launcher game) {
        super(game, "Play");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        launcher.getGame().changeState(BombermanGame.State.SINGlE);
        launcher.dispose();
    }
}
