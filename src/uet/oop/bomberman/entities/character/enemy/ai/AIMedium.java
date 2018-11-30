package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.character.enemy.ai.aStarAlgorithm.AStarPathFinder;
import uet.oop.bomberman.entities.character.enemy.ai.aStarAlgorithm.TileMap;
import uet.oop.bomberman.entities.character.movement.Direction;

public class AIMedium extends AI {
	Bomber _bomber;
	AStarPathFinder _pathFinder;
	TileMap _map;
	Board _board;

	public AIMedium(Board board, Bomber bomber, Enemy e) {
		super(e);
	    _bomber = bomber;
	    _board = board;
	    _map = new TileMap(board, e);
	    _pathFinder = new AStarPathFinder(_map, e);
	}

	@Override
	public Direction calculateDirection() {
		// TODO: cài đặt thuật toán tìm đường đi
        int sx = _e.getXTile();
        int sy = _e.getYTile();
        int tx = sx, ty = sy;

        try {
            tx = _bomber.getXTile();
            ty = _bomber.getYTile();
        } catch (NullPointerException e) {
            _bomber = _board.getBomber();
            System.err.println("Null player");
        }

        Direction direction = _pathFinder.findPath(_e, sx, sy, tx, ty);
        if (_board.getInput().vk_j) {
            _map.display();
        }

        return direction;
	}
}
