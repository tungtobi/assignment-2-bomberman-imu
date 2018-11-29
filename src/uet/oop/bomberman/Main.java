package uet.oop.bomberman;

import uet.oop.bomberman.exceptions.GameException;
import uet.oop.bomberman.input.FileDataManager;

public class Main
{
    public static void main(String[] args) throws GameException
    {
        new BombermanGame().run();
    }
}
