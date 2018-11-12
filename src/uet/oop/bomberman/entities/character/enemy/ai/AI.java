package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.character.movement.Direction;
import uet.oop.bomberman.entities.character.movement.DirectionConverter;

import java.util.Random;

public abstract class AI {

    protected Enemy _e;
	protected Random random = new Random();

	public AI() {

    }

	public AI(Enemy e) {
        _e = e;
    }

	/**
	 * Thuật toán tìm đường đi
	 * @return hướng đi xuống/phải/trái/lên tương ứng với các giá trị 0/1/2/3
	 */
	public abstract Direction calculateDirection();

	public Direction randomDirection() {
        Direction curDir = _e.getDirection();
        Direction ret = DirectionConverter.fromInt(random.nextInt(4));

        while (ret == curDir)
            ret = DirectionConverter.fromInt(random.nextInt(4));

        return ret;
    }
}
