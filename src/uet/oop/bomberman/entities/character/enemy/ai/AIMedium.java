package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.character.enemy.ai.aStarAlgorithm.AStar;
import uet.oop.bomberman.entities.character.movement.Direction;
import uet.oop.bomberman.entities.tile.Tile;

public class AIMedium extends AI {
	Bomber _bomber;
	AStar aStar;

	public AIMedium(Bomber bomber, Enemy e) {
		super(e);
	    _bomber = bomber;
	    //aStar = new AStar(board, e, bomber);
	}

	@Override
	public Direction calculateDirection() {
		// TODO: cài đặt thuật toán tìm đường đi
        if (!isBomberNearly()) {
            return _e.getDirection();
        }
        return followBomber();
	}

	private boolean isBomberNearly() {
	    return getDistance() <= 80;
    }

	private boolean canEnemyMove(Direction _direction) {
        double xa = 0;
        double ya = 0;

        double _speed = _e.getSpeed();

        if (_direction == Direction.UP)
            ya -= _speed;
        else if (_direction == Direction.RIGHT)
            xa += _speed;
        else if (_direction == Direction.DOWN)
            ya += _speed;
        else if (_direction == Direction.LEFT)
            xa -= _speed;

        return _e.canMove(_e.getX() + xa, _e.getY() + ya);
    }

	private Direction followBomber() {
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

	public Direction checkoutBomber() {
        Direction dir = _e.getDirection();

        if (_bomber == null)
            return dir;

        if (Math.abs(_bomber.getX() - _e.getX()) < 0.5) {
            //_e.setSpeed(Game.getBomberSpeed());
            dir = calculateRowDirection();
        }
        if (Math.abs(_bomber.getY() - _e.getY()) < 0.5) {
            //_e.setSpeed(Game.getBomberSpeed());
            dir = calculateColDirection();
        }

        return dir;
    }

    private Direction calculateColDirection() {
        if (_bomber == null)
            return Direction.NONE;

        if(_bomber.getX() < _e.getX())
            return Direction.LEFT;
        else if(_bomber.getX() > _e.getX())
            return Direction.RIGHT;

        return Direction.NONE;
    }

    private Direction calculateRowDirection() {
	    if (_bomber == null)
	        return Direction.NONE;

        if(_bomber.getY() < _e.getY())
            return Direction.UP;
        else if(_bomber.getY() > _e.getY())
            return Direction.DOWN;

        return Direction.NONE;
    }

	public double getDistance() {
	    if (_bomber == null)
	        return -1;

	    double deltaX = _bomber.getX() - _e.getX();
	    double deltaY = _bomber.getY() - _e.getY();

	    return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
