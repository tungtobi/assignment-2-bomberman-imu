package uet.oop.bomberman;

import uet.oop.bomberman.gui.Frame;

import uet.oop.bomberman.exceptions.GameException;
import uet.oop.bomberman.gui.Frame;
import uet.oop.bomberman.gui.Launcher;

import java.awt.*;
import java.util.Scanner;

public class BombermanGame
{
	public enum STATE
	{
		MENU,
		GAME
	}

	private STATE state = STATE.MENU;

	public void run() throws GameException
	{
		Launcher launcher = null;
		while (true)
		{
			System.out.println(state);
			if (state == STATE.GAME)
			{
				Frame mainwindow = new Frame();
				state = STATE.MENU;
				launcher = null;
			} else if (state == STATE.MENU)
			{
				if (launcher == null)
					launcher = new Launcher(this);
				if (!launcher.isDisplayable())
				{
					System.exit(0);
				}
			}
		}
	}

	public void changeState(STATE state)
	{
		this.state = state;
	}
}
