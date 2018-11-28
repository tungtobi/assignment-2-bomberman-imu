package uet.oop.bomberman.gui.button;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.gui.Launcher;

import java.awt.event.ActionEvent;

public class BackButton extends Button {
    public BackButton(Launcher game) {
        super(game, "Back");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        onClickSound();
        launcher.getInfoPanel().setVisible(false);
        launcher.getMenuPanel().setVisible(true);
    }
}
