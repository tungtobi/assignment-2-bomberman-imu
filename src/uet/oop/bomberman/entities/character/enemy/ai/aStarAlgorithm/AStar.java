package uet.oop.bomberman.entities.character.enemy.ai.aStarAlgorithm;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.character.movement.Direction;
import uet.oop.bomberman.entities.character.movement.DirectionConverter;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.level.Coordinates;

import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class AStar {
    private ArrayList<Node> closed = new ArrayList<Node>();
    private PriorityQueue<Node> open = new PriorityQueue<Node>();
    private TileMap map;
    private int maxSearchDistance = 100;

    private Node[][] nodes;
    private AStarHeuristic heuristic;
    private Node current;

    private Enemy enemy;
    private int sourceX, sourceY;
    private int distance;

    public AStar(TileMap map) {
        heuristic = new AStarHeuristic();
        this.map = map;

        nodes = new Node[map.getHeight()][map.getWidth()];
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                nodes[y][x] = new Node(x, y);
            }
        }
    }

    public Direction findPath(Enemy enemy, int sx, int sy, int tx, int ty) {
        current = null;

        this.enemy = enemy;
        this.sourceX = tx;
        this.sourceY = ty;
        this.distance = 0;

        map.update();

        if (map.blocked(tx, ty)) {
            return Direction.NONE;
        }

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                nodes[y][x].reset();
            }
        }

        nodes[sy][sx].cost = 0;
        nodes[sy][sx].depth = 0;
        closed.clear();
        open.clear();
        addToOpen(nodes[sy][sx]);

        nodes[ty][tx].parent = null;

        int maxDepth = 0;
        while (maxDepth < maxSearchDistance && open.size() != 0) {
            int lx = sx;
            int ly = sy;
            if (current != null) {
                lx = current.x;
                ly = current.y;
            }

            current = getFirstInOpen();
            distance = current.depth;

            if (current == nodes[ty][tx]) {
                if (isValidLocation(enemy, lx, ly, tx, ty)) {
                    break;
                }
            }

            removeFromOpen(current);
            addToClosed(current);

            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    if (x == 0 && y == 0) {
                        continue;
                    }

                    if (x != 0 && y != 0) {
                        continue;
                    }

                    int xp = x + current.x;
                    int yp = y + current.y;

                    if (isValidLocation(enemy, current.x, current.y, xp, yp)) {
                        float nextStepCost = current.cost + 1;
                        Node neighbour = nodes[yp][xp];

                        if (nextStepCost < neighbour.cost) {
                            if (inOpenList(neighbour)) {
                                removeFromOpen(neighbour);
                            }
                            if (inClosedList(neighbour)) {
                                removeFromClosed(neighbour);
                            }
                        }

                        if (!inOpenList(neighbour) && !inClosedList(neighbour)) {
                            neighbour.cost = nextStepCost;
                            neighbour.heuristic = heuristic.getCost(map, xp, yp, tx, ty);
                            maxDepth = Math.max(maxDepth, neighbour.setParent(current));
                            addToOpen(neighbour);
                        }
                    }
                }
            }
        }

        if (nodes[ty][tx].parent == null) {
            return Direction.NONE;
        }

        Node target = nodes[ty][tx];
        while (target.parent != nodes[sy][sx]) {
            target = target.parent;
        }

        return calculateDirection(target.x, target.y);
    }

    private Direction calculateDirection(int targX, int targY) {
        double tx = Coordinates.tileToPixel(targX);
        double ty = Coordinates.tileToPixel(targY + 1);
        double sx = enemy.getX();
        double sy = enemy.getY();

        int vertical = (new Random()).nextInt(2);
        Direction dir;

        switch (vertical) {
            case 1:
                dir = calculateRowDirection(ty);
                if (dir == Direction.NONE)
                    dir = calculateColDirection(tx);
                break;
            default:
                dir = calculateColDirection(tx);
                if (dir == Direction.NONE)
                    dir = calculateRowDirection(ty);
                break;
        }

        return dir;
    }

    private Direction calculateColDirection(double tx) {

        if(tx < enemy.getX())
            return Direction.LEFT;
        else if(tx > enemy.getX())
            return Direction.RIGHT;

        return Direction.NONE;
    }

    private Direction calculateRowDirection(double ty) {
        if(ty < enemy.getY())
            return Direction.UP;
        else if(ty > enemy.getY())
            return Direction.DOWN;

        return Direction.NONE;
    }

    private boolean isValidLocation(Enemy enemy, int sx, int sy, int x, int y) {
        boolean invalid = x < 0 || y < 0 || x > map.getWidth() || y > map.getHeight();

        if (!invalid && (sx != x || sy != y)) {
            this.enemy = enemy;
            this.sourceX = sx;
            this.sourceY = sy;
            invalid = map.blocked(x, y);
        }

        return !invalid;
    }

    private Node getFirstInOpen() {
        return (Node) open.peek();
    }

    private boolean inOpenList(Node node) {
        return node.isOpen();
    }

    private void addToOpen(Node node) {
        node.setOpen(true);
        open.add(node);
    }

    private void removeFromOpen(Node node) {
        node.setOpen(false);
        open.remove(node);
    }

    private void addToClosed(Node node) {
        node.setClosed(true);
        closed.add(node);
    }

    private boolean inClosedList(Node node) {
        return node.isClosed();
    }

    private void removeFromClosed(Node node) {
        node.setClosed(false);
        closed.remove(node);
    }

    private class Node implements Comparable {
        private int x, y;
        private float cost;
        private Node parent;
        private float heuristic;
        private int depth;
        private boolean open;
        private boolean closed;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int setParent(Node parent) {
            depth = parent.depth + 1;
            this.parent = parent;

            return depth;
        }

        public int compareTo(Object other) {
            if (!(other instanceof Node))
                return -1;

            Node o = (Node) other;

            float f = heuristic + cost;
            float of = o.heuristic + o.cost;

            if (f < of) {
                return -1;
            } else if (f > of) {
                return 1;
            } else {
                return 0;
            }
        }

        public void setOpen(boolean open) {
            this.open = open;
        }

        public boolean isOpen() {
            return open;
        }

        public void setClosed(boolean closed) {
            this.closed = closed;
        }

        public boolean isClosed() {
            return closed;
        }

        public void reset() {
            closed = false;
            open = false;
            cost = 0;
            depth = 0;
        }

        @Override
        public String toString() {
            return "[Node " + x + ", " + y + "]";
        }
    }
}
