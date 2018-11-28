package uet.oop.bomberman.entities.character.enemy.ai.aStarAlgorithm;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;

public class TileMap {
    private Board board;
    private int width, height;
    private char[][] map;
    private Character character;

    public TileMap(Board board, Character charact) {
        this.board = board;
        width = board.getWidth();
        height = board.getHeight();
        map = new char[height][width];
        character = charact;
    }

    public void update() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board.blockedAt(character, x, y)) {
                    map[y][x] = 'x';
                } else {
                    map[y][x] = ' ';
                }
            }
        }
    }

    private void display() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(map[y][x]);
            }
            System.out.println();
        }
    }

    public boolean blocked(int x, int y) {
        return map[y][x] == 'x';
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
