package uet.oop.bomberman.entities.character.enemy;


import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.character.enemy.ai.AIMedium;
import uet.oop.bomberman.entities.character.movement.Direction;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
	
	public Oneal(int x, int y, Board board) {
		super(x, y, board, Sprite.oneal_dead, Game.getEnemySpeed() * 0.75, 200);
		
		_sprite = Sprite.oneal_left1;
		
		_ai = new AIMedium(_board.getBomber(), this);
		_direction  = _ai.randomDirection();
	}

    @Override
    public void calculateMove() {
        double xa = 0;
        double ya = 0;

		_direction = _ai.calculateDirection();

		if (_direction == Direction.UP)
            ya -= _speed;
        else if (_direction == Direction.RIGHT)
            xa += _speed;
        else if (_direction == Direction.DOWN)
            ya += _speed;
        else if (_direction == Direction.LEFT)
            xa -= _speed;

        _moving = (xa != 0) || (ya != 0);

        if (canMove(_x + xa, _y + ya)) {
            move(xa, ya);
        } else {
            _direction = _ai.randomDirection();
        }
	}

    @Override
	protected void chooseSprite() {
		switch(_direction) {
			case UP:
			case RIGHT:
				if(_moving)
					_sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, _animate, 60);
				else
					_sprite = Sprite.oneal_left1;
				break;
            case DOWN:
            case LEFT:
				if(_moving)
					_sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, _animate, 60);
				else
					_sprite = Sprite.oneal_left1;
				break;
		}
	}
}
