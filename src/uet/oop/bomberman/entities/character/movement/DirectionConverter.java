package uet.oop.bomberman.entities.character.movement;

public class DirectionConverter {

    public static Direction fromInt(int direction) {
        Direction ret = Direction.NONE;
        switch (direction) {
            case 0:
                ret = Direction.UP;
                break;
            case 1:
                ret = Direction.RIGHT;
                break;
            case 2:
                ret = Direction.DOWN;
                break;
            case 3:
                ret = Direction.LEFT;
                break;
        }

        return ret;
    }

    public static int toInt(Direction direction) {
        int ret = 4;

        switch (direction) {
            case UP:
                ret = 0;
                break;
            case DOWN:
                ret = 2;
                break;
            case LEFT:
                ret = 3;
                break;
            case RIGHT:
                ret = 1;
                break;
        }

        return ret;
    }
}
