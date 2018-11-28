package uet.oop.bomberman.gui.button;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.gui.Launcher;
import uet.oop.bomberman.input.FileDataManager;

import java.awt.event.ActionEvent;

public class QuitButton extends Button {
    public QuitButton(Launcher game) {
        super(game, "Quit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        onClickSound();
        FileDataManager manager = new FileDataManager();
        manager.exportData();
        System.exit(0);
    }
}