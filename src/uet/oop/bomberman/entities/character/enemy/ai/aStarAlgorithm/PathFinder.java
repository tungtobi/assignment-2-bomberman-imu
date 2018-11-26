package uet.oop.bomberman.entities.character.enemy.ai.aStarAlgorithm;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.character.movement.Direction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathFinder implements AStarHeuristic {
    private Board _board;

    @Override
    public double getCost(double x, double y, double tx, double ty) {
        double deltaX = x - tx;
        double deltaY = y - ty;

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

}
