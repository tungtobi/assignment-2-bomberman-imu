package uet.oop.bomberman.gui;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.audio.MyAudioPlayer;
import uet.oop.bomberman.input.FileDataManager;

import javax.swing.*;
import java.awt.*;

/**
 * Swing Frame chứa toàn bộ các component
 */
public class Frame extends JFrame {
	
	public GamePanel _gamepane;
	private JPanel _containerpane;
	private InfoPanel _infopanel;
	private BombermanGame.State _mode;
	
	private Game _game;

	public Frame(BombermanGame.State mode) {
		_mode = mode;
		_containerpane = new JPanel(new BorderLayout());
		_gamepane = new GamePanel(this);
		_infopanel = new InfoPanel(_gamepane.getGame());

		_containerpane.add(_infopanel, BorderLayout.PAGE_START);
		_containerpane.add(_gamepane, BorderLayout.PAGE_END);
		
		_game = _gamepane.getGame();

		add(_containerpane);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setIconImage(new ImageIcon(getClass().getResource("/images/icon.jpeg")).getImage());


		_game.start();
		FileDataManager manager = new FileDataManager();
		manager.exportData();
		dispose();
	}

	public BombermanGame.State getMode() {
		return _mode;
	}

	public void setTime(int time) {
		_infopanel.setTime(time);
	}
	
	public void setPoints(int points) {
		_infopanel.setPoints(points);
	}
	
}
