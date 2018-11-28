package uet.oop.bomberman.gui.button;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.gui.Launcher;

import java.awt.event.ActionEvent;

public class HowToPlayButton extends Button {
    public HowToPlayButton(Launcher game) {
        super(game, "How to play");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        launcher.getInfoPanel().setVisible(true);
        launcher.getMenuPanel().setVisible(false);
    }
}
