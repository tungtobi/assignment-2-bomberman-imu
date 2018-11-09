package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.character.enemy.Enemy;


public class AILow extends AI {
	 Enemy _e;

	 public AILow(Enemy e)
	 {
	 	_e = e;
	 }

	@Override
	public int calculateDirection() {
		// TODO: cài đặt thuật toán tìm đường đi
		int curDir = _e.getDirection();
		int ret = random.nextInt(4);
		while (ret == curDir)
			ret = random.nextInt(4);
		return ret;
	}

}
