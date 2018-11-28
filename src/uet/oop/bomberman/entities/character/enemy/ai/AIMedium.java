package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.character.enemy.ai.aStarAlgorithm.AStar;
import uet.oop.bomberman.entities.character.enemy.ai.aStarAlgorithm.TileMap;
import uet.oop.bomberman.entities.character.movement.Direction;
import uet.oop.bomberman.entities.tile.Tile;

public class AIMedium extends AI {
	Bomber _bomber;
	AStar aStar;
	TileMap map;

	public AIMedium(Board board, Bomber bomber, Enemy e) {
		super(e);
	    _bomber = bomber;
	    map = new TileMap(board, e);
	    aStar = new AStar(map);
	}

	@Override
	public Direction calculateDirection() {
		// TODO: cài đặt thuật toán tìm đường đi
        int sx = _e.getXTile();
        int sy = _e.getYTile();
        int tx = _bomber.getXTile();
        int ty = _bomber.getYTile();

        Direction direction = aStar.findPath(_e, sx, sy, tx, ty);

        return direction;
	}
}
