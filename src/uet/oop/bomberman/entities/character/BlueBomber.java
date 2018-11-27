package uet.oop.bomberman.entities.character;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.character.movement.Direction;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;

public class BlueBomber extends Bomber {

    public BlueBomber(int x, int y, Board board) {
        super(x, y, board);
        _bombs = _board.getBombs();
        _input = _board.getInput();
        _sprite = Sprite.player2_right;
    }

    @Override
    public void render(Screen screen) {
        calculateXOffset();

        if (_alive)
            chooseSprite();
        else {
            if(_timeAfter > 0) {
                _sprite = Sprite.player2_dead1;
            } else {
                _sprite = Sprite.movingSprite(Sprite.player2_dead1, Sprite.player2_dead2, Sprite.player2_dead3, _animate, 10);
            }
        }

        screen.renderEntity((int) _x, (int) _y - _sprite.SIZE, this);
    }

    @Override
    protected void calculateMove() {
        double bomberSpeed = this.getBomberSpeed();
        double xa = _x + ((_input.vk_a ? 1: 0) * -1 + (_input.vk_d ? 1 : 0)) * bomberSpeed;
        double ya = _y + ((_input.vk_w ? 1 : 0) * -1 + (_input.vk_s ? 1 : 0)) * bomberSpeed;
        _moving = (xa != _x) || (ya != _y);
        move(xa, ya);

        if (!_moving) {
            int tilesSize = Game.TILES_SIZE;

            int tileX = Coordinates.pixelToTile(_x);
            if ((tileX + 1) * tilesSize * 1.0 - _x < 1.5)
                _x = Coordinates.tileToPixel(tileX + 1);
            if (_x - tileX * tilesSize * 1.0  < 1.5)
                _x = Coordinates.tileToPixel(tileX);

            int tileY = Coordinates.pixelToTile(_y);
            if ((tileY + 1) * tilesSize * 1.0  - _y < 2.5)
                _y = Coordinates.tileToPixel(tileY + 1);
            if (_y - tileY * tilesSize * 1.0 < 2.5)
                _y = Coordinates.tileToPixel(tileY);
        }
    }

    @Override
    protected void detectPlaceBomb() {
        if (_input.vk_j && _timeBetweenPutBombs == 0
                && this.getBombRate() > 0 && _board.getBombAt(getXTile(), getYTile()) == null) {
            placeBomb(getXTile(), getYTile());
            _timeBetweenPutBombs = TIME_TO_PUT_BOMB;
            this.addBombRate(-1);
        }

//        System.out.println(getBombRate());
    }

    @Override
    public void move(double xa, double ya) {
        // TODO: sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không và thực hiện thay đổi tọa độ _x, _y
        // TODO: nhớ cập nhật giá trị _direction sau khi di chuyển

        if (_input.vk_w) {
            _direction = Direction.UP;
        } else if (_input.vk_d) {
            _direction = Direction.RIGHT;
        } else if (_input.vk_s)  {
            _direction = Direction.DOWN;
        } else if (_input.vk_a)  {
            _direction = Direction.LEFT;
        }

        if(canMove(_x, ya)) {
            _y = ya;
        }

        if(canMove(xa, _y)) {
            _x = xa;
        }
    }

    @Override
    protected void chooseSprite() {
        switch (_direction) {
            case UP:
                _sprite = Sprite.player2_up;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player2_up_1, Sprite.player2_up_2, _animate, 20);
                }
                break;
            case RIGHT:
                _sprite = Sprite.player2_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player2_right_1, Sprite.player2_right_2, _animate, 20);
                }
                break;
            case DOWN:
                _sprite = Sprite.player2_down;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player2_down_1, Sprite.player2_down_2, _animate, 20);
                }
                break;
            case LEFT:
                _sprite = Sprite.player2_left;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player2_left_1, Sprite.player2_left_2, _animate, 20);
                }
                break;
            default:
                _sprite = Sprite.player2_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player2_right_1, Sprite.player2_right_2, _animate, 20);
                }
                break;
        }
    }
}
