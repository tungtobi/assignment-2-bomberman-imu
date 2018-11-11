package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.character.movement.Direction;

public class AIMedium extends AI {
	Bomber _bomber;

	public AIMedium(Bomber bomber, Enemy e) {
		super(e);
	    _bomber = bomber;
	}

	@Override
	public Direction calculateDirection() {
		// TODO: cài đặt thuật toán tìm đường đi
        double distance = getDistance();
        double _x = _e.getX(), _y = _e.getY();
        Direction _dir = _e.getDirection();
        double _speed = _e.getSpeed();

        if      (_dir == Direction.UP) _y -= _speed;
        else if (_dir == Direction.RIGHT) _x += _speed;
        else if (_dir == Direction.DOWN) _y += _speed;
        else if (_dir == Direction.LEFT) _x -= _speed;

        if (_bomber == null || distance > 80 || !_e.canMove(_x, _y)) {
            return randomDirection();
        } else {
            int vertical = random.nextInt(2);
            Direction dir;

            switch (vertical) {
                case 1:
                    dir = calculateRowDirection();
                    if (dir == Direction.NONE)
                        dir = calculateColDirection();
                    break;
                default:
                    dir = calculateColDirection();
                    if (dir == Direction.NONE)
                        dir = calculateRowDirection();
                    break;
            }

            return dir;
        }
	}

    private Direction calculateColDirection() {
        if (_bomber == null)
            return Direction.NONE;

        if(_bomber.getXTile() < _e.getXTile())
            return Direction.LEFT;
        else if(_bomber.getXTile() > _e.getXTile())
            return Direction.RIGHT;

        return Direction.NONE;
    }

    private Direction calculateRowDirection() {
	    if (_bomber == null)
	        return Direction.NONE;

        if(_bomber.getYTile() < _e.getYTile())
            return Direction.UP;
        else if(_bomber.getYTile() > _e.getYTile())
            return Direction.DOWN;

        return Direction.NONE;
    }

	private double getDistance() {
	    if (_bomber == null)
	        return -1;

	    double deltaX = _bomber.getX() - _e.getX();
	    double deltaY = _bomber.getY() - _e.getY();

	    return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
