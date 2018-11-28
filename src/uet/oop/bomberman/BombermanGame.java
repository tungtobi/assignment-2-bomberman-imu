package uet.oop.bomberman;

import uet.oop.bomberman.audio.MyAudioPlayer;
import uet.oop.bomberman.gui.Frame;

import uet.oop.bomberman.exceptions.GameException;
import uet.oop.bomberman.gui.Frame;
import uet.oop.bomberman.gui.Launcher;

import java.awt.*;
import java.util.Scanner;

public class BombermanGame
{
	public enum State
	{
		MENU,
		SINGlE,
        MULTY
	}

	private State state = State.MENU;

	public static MyAudioPlayer musicPlayer;

	public void run() throws GameException
	{
		Launcher launcher = null;

        musicPlayer = new MyAudioPlayer(MyAudioPlayer.BACKGROUND_MUSIC);
        //musicPlayer.loop();

        while (true) {
			System.out.println(state);
			if (state == State.SINGlE || state == State.MULTY) {
				Frame mainwindow = new Frame(state);
				state = State.MENU;

				launcher = null;
			} else if (state == State.MENU) {
				if (launcher == null)
					launcher = new Launcher(this);
				if (!launcher.isDisplayable()) {
					System.exit(0);
				}
			}
		}
	}

	public void changeState(State state) {
		this.state = state;
	}
}
