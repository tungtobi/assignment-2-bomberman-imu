package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.audio.MyAudioPlayer;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Item extends Tile {
	protected boolean _active;

	public Item(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		_active = false;
	}

	public abstract void effect(Character character);

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý Bomber ăn Item
		if (e instanceof Bomber) {
			MyAudioPlayer powerUpAudio = new MyAudioPlayer(MyAudioPlayer.POWER_UP);
			powerUpAudio.play();

			if (!_active) {
				_active = true;
				effect((Bomber) e);
			}

			remove();

			return true;
		}

		if (e instanceof Flame || e instanceof Enemy) {
            MyAudioPlayer powerUpAudio = new MyAudioPlayer(MyAudioPlayer.POWER_UP);
            powerUpAudio.play();

			remove();
			return true;
		}

		return false;
	}
}
