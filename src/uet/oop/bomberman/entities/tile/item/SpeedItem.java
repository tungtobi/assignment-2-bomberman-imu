package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.Game;

public class SpeedItem extends Item {

	public SpeedItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public void effect(Character character) {
		if (character instanceof Bomber) {
            ((Bomber) character).addBomberSpeed(0.3);
        } else if (character instanceof Enemy) {
            ((Enemy) character).addSpeed();
        }
	}
}
