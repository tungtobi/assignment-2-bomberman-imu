package uet.oop.bomberman.entities.character.enemy.ai.aStarAlgorithm;

import java.util.PriorityQueue;

public class PathFinder implements AStarHeuristic {


    @Override
    public double getCost(double x, double y, double tx, double ty) {
        double deltaX = x - tx;
        double deltaY = y - ty;

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    //private int calculateNextMove()
}
