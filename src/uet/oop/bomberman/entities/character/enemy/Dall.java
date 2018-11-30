package uet.oop.bomberman.entities.character.enemy;


import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.character.enemy.ai.AILow;
import uet.oop.bomberman.entities.character.enemy.ai.AIMedium;
import uet.oop.bomberman.graphics.Sprite;

public class Dall extends Enemy {

	public Dall(char id, int x, int y, Board board) {
		super(id, x, y, board, Sprite.dall_dead, Game.getEnemySpeed() * 0.75, 200);

		_sprite = Sprite.dall_left1;

		_ai = new AILow(this);
		_direction = _ai.calculateDirection();
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
