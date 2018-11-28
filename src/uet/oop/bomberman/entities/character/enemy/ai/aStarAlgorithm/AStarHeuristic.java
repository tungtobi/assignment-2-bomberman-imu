package uet.oop.bomberman.entities.character.enemy.ai.aStarAlgorithm;

public class AStarHeuristic {
    /**
     * Tính khoảng cách giữa 2 điểm
     * @param x hoành độ điểm đầu
     * @param y tung độ điểm đầu
     * @param tx hoành độ điểm cuối
     * @param ty tung độ điểm cuối
     * @return khoảng cách giữa 2 điểm
     */
    public float getCost(int x, int y, int tx, int ty) {
        int dx = tx - x;
        int dy = ty - y;

        float result = (float) Math.sqrt(dx * dx + dy * dy);

        return result;
    }
}
