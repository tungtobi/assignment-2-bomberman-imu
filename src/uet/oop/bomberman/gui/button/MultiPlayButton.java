package uet.oop.bomberman.gui.button;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.gui.Launcher;

import java.awt.event.ActionEvent;

public class MultiPlayButton extends Button {
    public MultiPlayButton(Launcher game) {
        super(game, "Multi Play");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        onClickSound();
        launcher.getGame().changeState(BombermanGame.State.MULTY);
        launcher.dispose();
    }
}