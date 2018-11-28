package uet.oop.bomberman.entities.character.enemy;


import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.character.enemy.ai.AIMedium;
import uet.oop.bomberman.graphics.Sprite;

public class Dall extends SmartEnemy {

	public Dall(int x, int y, Board board) {
		super(x, y, board, Sprite.dall_dead, Game.getEnemySpeed(), 200);
		
		_sprite = Sprite.dall_left1;
	}

    @Override
	protected void chooseSprite() {
		switch(_direction) {
			case UP:
			case RIGHT:
				if(_moving)
					_sprite = Sprite.movingSprite(Sprite.dall_right1, Sprite.dall_right2, Sprite.dall_right3, _animate, 60);
				else
					_sprite = Sprite.dall_left1;
				break;
            case DOWN:
            case LEFT:
				if(_moving)
					_sprite = Sprite.movingSprite(Sprite.dall_left1, Sprite.dall_left2, Sprite.dall_left3, _animate, 60);
				else
					_sprite = Sprite.dall_left1;
				break;
		}
	}
}
