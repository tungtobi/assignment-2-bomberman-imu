package uet.oop.bomberman.gui.button;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.gui.Launcher;
import uet.oop.bomberman.gui.MyColor;
import uet.oop.bomberman.gui.MyFont;
import uet.oop.bomberman.input.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public abstract class Button extends JButton implements ActionListener {
    protected Launcher launcher;

    private String _text;
    public static final int WIDTH = 200, HEIGHT = 50;

    public Button(Launcher game, String text) {
        _text = text;
        launcher = game;
        initialize();
    }

    private void initialize() {
        setBackground(MyColor.DARK);
        setFont(MyFont.MEDIUM);
        setForeground(MyColor.WHITE);
        setText(_text);
        setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setBorderPainted(false);
        setFocusPainted(false);
        addActionListener(this);
        addMouseListener(new Mouse());
    }
}
