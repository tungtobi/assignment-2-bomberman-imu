package uet.oop.bomberman.entities.character.enemy.ai.aStarAlgorithm;

public class AStarHeuristic {
    public float getCost(TileMap map, int x, int y, int tx, int ty) {
        int dx = tx - x;
        int dy = ty - y;

        float result = (float) Math.sqrt(dx * dx + dy * dy);

        return result;
    }
}
