package uet.oop.bomberman.gui.button;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.gui.Launcher;

import java.awt.event.ActionEvent;

public class QuitButton extends Button {
    public QuitButton(Launcher game) {
        super(game, "Quit game");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        onClickSound();
        System.exit(0);
    }
}