package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.Game;

public class BombItem extends Item {

	public BombItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public void effect()
	{
		Game.addBombRate(1);
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý Bomber ăn Item
		if (e instanceof Bomber)
		{
			if (!_active)
			{
				_active = true;
				effect();
			}
			remove();

			return true;
		}
		return false;
	}
}
