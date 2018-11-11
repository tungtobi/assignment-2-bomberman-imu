package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.character.movement.Direction;


public class AILow extends AI {
	public AILow(Enemy e) {
	    super(e);
    }

	@Override
	public Direction calculateDirection() {
		// TODO: cài đặt thuật toán tìm đường đi
		return randomDirection();
	}

}
