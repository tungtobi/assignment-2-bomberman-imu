package uet.oop.bomberman.entities.character.enemy.ai.aStarAlgorithm;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.character.Character;

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

    /**
     * Cập nhật lại bản đồ từ board game
     */
    public void update() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // bị chặn tại (x, y)
                if (board.blockedAt(character, x, y)) {
                    map[y][x] = 'x';
                } else {
                    map[y][x] = ' ';
                }
            }
        }
    }

    /**
     * Hiển thị bản đồ
     */
    public void display() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(map[y][x]);
            }
            System.out.println();
        }
    }

    /**
     * Kiểm tra điểm (x, y) có bị chặn không
     * @param x hoành độ điểm xét
     * @param y tung độ điểm xét
     * @return true nếu bị chặn và false nếu ngược lại
     */
    public boolean blocked(int x, int y) {
        return map[y][x] == 'x';
    }

    /**
     * Lấy chiều dài bản đồ
     * @return chiều dài bản đồ
     */
    public int getWidth() {
        return width;
    }

    /**
     * Lấy chiều rộng bản đồ
     * @return chiều rộng bản đồ
     */
    public int getHeight() {
        return height;
    }
}
