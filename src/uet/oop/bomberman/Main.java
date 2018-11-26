package uet.oop.bomberman;

import uet.oop.bomberman.exceptions.GameException;

public class Main
{
    public static void main(String[] args) throws GameException
    {
        new BombermanGame().run();
    }
}
