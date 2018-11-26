package uet.oop.bomberman.gui;

import uet.oop.bomberman.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;


/**
 * Swing Panel hiển thị thông tin thời gian, điểm mà người chơi đạt được
 */
public class InfoPanel extends JPanel {
	
	private JLabel timeLabel;
	private JLabel pointsLabel;

	public InfoPanel(Game game) {
		setLayout(new GridLayout());

//		Font fontPixel = null;
//
//        try {
//            InputStream in = getClass().getResourceAsStream("/font/OCR-a___.ttf");
//            fontPixel = Font.createFont(Font.TRUETYPE_FONT, in);
//            fontPixel.deriveFont(50.0f);
//        } catch (FontFormatException | IOException e) {
//            e.printStackTrace();
//        }
//
		timeLabel = new JLabel("Time: " + game.getBoard().getTime());
		timeLabel.setForeground(Color.white);
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
//		timeLabel.setFont(fontPixel);

		pointsLabel = new JLabel("Points: " + game.getBoard().getPoints());
		pointsLabel.setForeground(Color.white);
		pointsLabel.setHorizontalAlignment(JLabel.CENTER);
//		pointsLabel.setFont(fontPixel);
		
		add(timeLabel);
		add(pointsLabel);
		
		setBackground(Color.black);
		setPreferredSize(new Dimension(0, 40));
	}
	
	public void setTime(int t) {
		timeLabel.setText("Time: " + t);
	}

	public void setPoints(int t) {
		pointsLabel.setText("Score: " + t);
	}
	
}
