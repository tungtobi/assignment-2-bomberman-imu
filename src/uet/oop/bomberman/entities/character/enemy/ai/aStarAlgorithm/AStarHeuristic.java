package uet.oop.bomberman.entities.character.enemy.ai.aStarAlgorithm;

public interface AStarHeuristic {
    public double getCost(double x, double y, double tx, double ty);
}
