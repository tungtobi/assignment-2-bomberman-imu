package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.character.enemy.ai.AIMedium;
import uet.oop.bomberman.entities.character.movement.Direction;
import uet.oop.bomberman.graphics.Sprite;

public abstract class SmartEnemy extends Enemy {

    public SmartEnemy(int x, int y, Board board, Sprite dead, double speed, int points) {
        super(x, y, board, dead, speed, points);
        _ai = new AIMedium(_board, _board.getBomber(), this);
        _direction  = _ai.randomDirection();
    }

    @Override
    public void calculateMove() {
        double xa = 0;
        double ya = 0;

        Direction dir = _ai.calculateDirection();
        if (dir != Direction.NONE) {
            _direction = dir;
        }

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
}
