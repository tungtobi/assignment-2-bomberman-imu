package uet.oop.bomberman.entities.character.enemy;


import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.enemy.ai.AIMedium;
import uet.oop.bomberman.entities.character.movement.Direction;
import uet.oop.bomberman.graphics.Sprite;

public class Pontan extends SmartEnemy {

	public Pontan(char id, int x, int y, Board board) {
		super(id, x, y, board, Sprite.pontan_dead, Game.getEnemySpeed(), 2000);
		
		_sprite = Sprite.pontan_left1;
	}

	@Override
	public boolean collide(Entity e) {
		if (e instanceof LayeredEntity)
			return true;

		return super.collide(e);
	}

    @Override
	protected void chooseSprite() {
		switch(_direction) {
			case UP:
			case RIGHT:
				if(_moving)
					_sprite = Sprite.movingSprite(Sprite.pontan_right1, Sprite.pontan_right2, Sprite.pontan_right3, _animate, 60);
				else
					_sprite = Sprite.pontan_left1;
				break;
            case DOWN:
            case LEFT:
				if(_moving)
					_sprite = Sprite.movingSprite(Sprite.pontan_left1, Sprite.pontan_left2, Sprite.pontan_left3, _animate, 60);
				else
					_sprite = Sprite.pontan_left1;
				break;
		}
	}
}
