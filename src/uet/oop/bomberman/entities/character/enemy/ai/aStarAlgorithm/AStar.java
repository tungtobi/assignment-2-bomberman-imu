package uet.oop.bomberman.entities.character.enemy.ai.aStarAlgorithm;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.character.movement.Direction;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;

import java.util.Map;
import java.util.PriorityQueue;

public class AStar {
    public static final int DIAGONAL_COST = 14;
    public static final int V_H_COST = 10;

    public class Cell {
        int heuristicCost;
        int finalCost;

        int i, j;

        public Cell(int _i, int _j) {
            i = _i;
            j = _j;
        }

        @Override
        public String toString() {
            return "(" + i + ", " + j + ")";
        }

        Cell parent;
    }

    private Board board;
    private Cell[][] grid;

    private PriorityQueue<Cell> open;

    private boolean[][] closed;

    private Enemy dest;
    private Bomber targ;

    private int destI, destJ, targI, targJ;

    public AStar(Board _board, Enemy enemy, Bomber bomber) {
        board = _board;

        open = new PriorityQueue<>((Object o1, Object o2) -> {
            Cell c1 = (Cell) o1;
            Cell c2 = (Cell) o2;

            if (c1.finalCost < c2.finalCost) return -1;
            else if (c1.finalCost > c2.finalCost) return 1;
            else return 0;
        });

        dest = enemy;
        destI = dest.getYTile();
        destJ = dest.getXTile();

        targ = bomber;
        targI = targ.getYTile();
        targJ = targ.getXTile();

        int col = board.getWidth();
        int row = board.getHeight();

        System.out.println(row + " " + col);

        loadMap(row, col);

        closed = new boolean[row][col];
    }

    public void loadMap(int row, int col) {
        grid = new Cell[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                boolean isBlocked = board.blockedAt(j, i);

                if (isBlocked) {
                    grid[i][j] = null;
//                    System.out.print("X");
                } else {
                    grid[i][j] = new Cell(i, j);
                    grid[i][j].heuristicCost = Math.abs(i - targI) + Math.abs(j - targJ);
//                    System.out.print(" ");
                }
            }

//            System.out.println();
        }

        grid[destI][destJ].finalCost = 0;
    }

    public void updateTarget(Bomber bomber) {
        targI = bomber.getYTile();
        targJ = bomber.getXTile();

//        int col = board.getWidth();
//        int row = board.getHeight();
//
//        display(row, col);
    }

    public void display(int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] != null)
                    System.out.print(" ");
                else System.out.print("x");
            }
            System.out.println();
        }
    }

    private void checkAndUpdateCost(Cell current, Cell t, int cost) {
        if (t == null || closed[t.i][t.j]) return;
        int tFinalCost = t.heuristicCost + cost;

        boolean inOpen = open.contains(t);
        if (!inOpen || tFinalCost < t.finalCost) {
            t.finalCost = tFinalCost;
            t.parent = current;

            if (!inOpen) open.add(t);
        }
    }

    public Direction calculateDirection() {
        findPath();

        if (closed[targI][targJ]) {

            Cell current = grid[targI][targJ];

            if (current != null) {
                while (current.parent != null) {
                    System.out.print(current + " -> ");
                    current = current.parent;
                }

                Direction direct = calculateNextMove(current, destI, destJ);
                return direct;
            } else return Direction.NONE;

        } else {
            System.out.println("No possible path");
            return Direction.NONE;
        }


    }


    private Direction calculateNextMove(Cell cur, int dstI, int dstJ) {
        if(cur.j < dstJ)
            return Direction.LEFT;
        else if(cur.j > dstJ)
            return Direction.RIGHT;

        if(cur.i < dstI)
            return Direction.UP;
        else if(cur.i > dstI)
            return Direction.DOWN;

        return Direction.NONE;
    }

    public void findPath() {
        open.add(grid[destI][destJ]);

        Cell current;

        while (true) {
            current = open.poll();
            if (current == null) break;
            closed[current.i][current.j] = true;

            if (current.equals(grid[targI][targJ]))
                return;

            Cell t;

            if (current.i - 1 >= 0) {
                t = grid[current.i - 1][current.j];
                checkAndUpdateCost(current, t, current.finalCost + V_H_COST);

                if (current.j - 1 > 0) {
                    t = grid[current.i - 1][current.j - 1];
                    checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
                }

                if (current.j + 1 < grid[0].length) {
                    t = grid[current.i - 1][current.j + 1];
                    checkAndUpdateCost(current, t, current.finalCost + DIAGONAL_COST);
                }
            }

            if (current.j - 1 >= 0) {
                t = grid[current.i][current.j - 1];
                checkAndUpdateCost(current, t, current.finalCost + V_H_COST);
            }

            if (current.j+1<grid[0].length){
                t = grid[current.i][current.j+1];
                checkAndUpdateCost(current, t, current.finalCost+V_H_COST);
            }

            if (current.i+1<grid.length){
                t = grid[current.i+1][current.j];
                checkAndUpdateCost(current, t, current.finalCost+V_H_COST);

                if (current.j-1>=0){
                    t = grid[current.i+1][current.j-1];
                    checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST);
                }

                if (current.j+1<grid[0].length){
                    t = grid[current.i+1][current.j+1];
                    checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST);
                }
            }
        }
    }
}
