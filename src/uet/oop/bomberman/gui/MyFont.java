package uet.oop.bomberman.gui;

import uet.oop.bomberman.Game;

import java.awt.*;

public class MyFont {
    private static final String name = "Manaspace";
    public static final Font TINY = new Font(name, Font.PLAIN, 14);
    public static final Font SMALL = new Font(name, Font.PLAIN, 18);
    public static final Font MEDIUM = new Font(name, Font.PLAIN, 20);
    public static final Font TITLE = new Font(name, Font.BOLD, 20 * Game.SCALE);
    public static final Font SUBTITLE = new Font(name, Font.PLAIN, 10 * Game.SCALE);
}
