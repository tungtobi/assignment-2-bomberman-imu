package uet.oop.bomberman.entities.character.enemy;


import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.enemy.ai.AIMedium;
import uet.oop.bomberman.entities.character.movement.Direction;
import uet.oop.bomberman.graphics.Sprite;

public class Ovape extends SmartEnemy {

	public Ovape(char id, int x, int y, Board board) {
		super(id, x, y, board, Sprite.ovape_dead, Game.getEnemySpeed() * 0.75, 600);
		
		_sprite = Sprite.ovape_left1;
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
					_sprite = Sprite.movingSprite(Sprite.ovape_right1, Sprite.ovape_right2, Sprite.ovape_right3, _animate, 60);
				else
					_sprite = Sprite.ovape_left1;
				break;
            case DOWN:
            case LEFT:
				if(_moving)
					_sprite = Sprite.movingSprite(Sprite.ovape_left1, Sprite.ovape_left2, Sprite.ovape_left3, _animate, 60);
				else
					_sprite = Sprite.ovape_left1;
				break;
		}
	}
}
