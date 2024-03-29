package uet.oop.bomberman.entities.character.enemy;

import sun.font.CoreMetrics;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.audio.MyAudioPlayer;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Message;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.bomb.FlameSegment;
import uet.oop.bomberman.entities.character.movement.Direction;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemy.ai.AI;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;
import uet.oop.bomberman.entities.tile.item.Item;

import java.awt.*;

public abstract class Enemy extends Character {

	protected int _points;
	
	protected double _speed;
	protected AI _ai;

	protected final double MAX_STEPS;
	protected final double rest;
	protected double _steps;
	
	protected int _finalAnimation = 30;
	protected Sprite _deadSprite;

	private EnemyId _id;
	
	public Enemy(int x, int y, Board board, Sprite dead, double speed, int points) {
		super(x, y, board);
		
		_points = points;
		_speed = speed;
		
		MAX_STEPS = Game.TILES_SIZE / _speed;
		rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
		_steps = MAX_STEPS;
		
		_timeAfter = 20;
		_deadSprite = dead;
	}

	public Enemy(char id, int x, int y, Board board, Sprite dead, double speed, int points) {
	    this(x, y, board, dead, speed, points);
	    setId(id);
    }
	
	@Override
	public void update() {
		animate();
		
		if(!_alive) {
			afterKill();
			return;
		}
		
		if(_alive) {
			calculateMove();
		}
	}
	
	@Override
	public void render(Screen screen) {
		
		if(_alive) {
		    chooseSprite();
		} else {
			if(_timeAfter > 0) {
				_sprite = _deadSprite;
				_animate = 0;
			} else {
				_sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 60);
			}
		}
			
		screen.renderEntity((int)_x, (int)_y - _sprite.SIZE, this);
	}
	
	@Override
	public void calculateMove() {
		// TODO: Tính toán hướng đi và di chuyển Enemy theo _ai và cập nhật giá trị cho _direction
		// TODO: sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không
		// TODO: sử dụng move() để di chuyển
		// TODO: nhớ cập nhật lại giá trị cờ _moving khi thay đổi trạng thái di chuyển

		double xa = 0;
		double ya = 0;

		if (_direction == Direction.UP)
			ya -= _speed;
		else if (_direction == Direction.RIGHT)
		    xa += _speed;
		else if (_direction == Direction.DOWN)
		    ya += _speed;
		else if (_direction == Direction.LEFT)
		    xa -= _speed;

		_moving = (xa != 0) || (ya != 0);

		if (canMove(_x + xa, _y + ya)) {
			move(xa, ya);
		} else
			_direction = _ai.calculateDirection();
	}
	
	@Override
	public void move(double xa, double ya) {
		if(!_alive) return;
		_y += ya;
		_x += xa;
	}
	
	@Override
	public boolean canMove(double x, double y) {
		// TODO: kiểm tra có đối tượng tại vị trí chuẩn bị di chuyển đến và có thể di chuyển tới đó hay không

		int spriteSize = _sprite.getSize();
        Entity entityBottomLeft  = _board.getEntity(Coordinates.pixelToTile(x), Coordinates.pixelToTile(y - 1), this);
        Entity entityBottomRight = _board.getEntity(Coordinates.pixelToTile(x + spriteSize - 1), Coordinates.pixelToTile(y - 1), this);
        Entity entityTopLeft     = _board.getEntity(Coordinates.pixelToTile(x), Coordinates.pixelToTile(y - spriteSize), this);
        Entity entityTopRight    = _board.getEntity(Coordinates.pixelToTile(x + spriteSize - 1), Coordinates.pixelToTile(y - spriteSize), this);

        if (collide(entityBottomRight) && collide(entityBottomLeft) && collide(entityTopLeft) && collide(entityTopRight))
            return true;
        return false;
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý va chạm với Flame
		// TODO: xử lý va chạm với Bomber

		if (e instanceof Bomber) {
			((Bomber)e).kill();
			return true;
		}

		if (e instanceof Enemy)
			return true;

		if (e instanceof Item)
			return true;

		return e.collide(this);
	}
	
	@Override
	public void kill() {
		if(!_alive) return;
		_alive = false;
		
		_board.addPoints(_points);

		Message msg = new Message("+" + _points, getXMessage(), getYMessage(), 2, Color.white, 14);
		_board.addMessage(msg);

		MyAudioPlayer placeSound = new MyAudioPlayer(MyAudioPlayer.ENEMY_DEAD);
		placeSound.play();
	}
	
	
	@Override
	protected void afterKill() {
		if(_timeAfter > 0) --_timeAfter;
		else {
			if(_finalAnimation > 0) --_finalAnimation;
			else
				remove();
		}
	}
	
	protected abstract void chooseSprite();

    public double getSpeed() {
        return _speed;
    }

    public void addSpeed() {
        _speed += 0.2;
    }

    public void setId(char id) {
        switch (id) {
            case '1':
                _id = EnemyId.BALLOON;
                break;
            case '2':
                _id = EnemyId.ONEAL;
                break;
            case '3':
                _id = EnemyId.DALL;
                break;
            case '4':
                _id = EnemyId.MINVO;
                break;
            case '5':
                _id = EnemyId.DORIA;
                break;
            case '6':
                _id = EnemyId.OVAPE;
                break;
            case '7':
                _id = EnemyId.PONTAN;
                break;
            case '8':
                _id = EnemyId.PASS;
                break;
        }
    }

    public EnemyId getId() {
        return _id;
    }

    public enum EnemyId {
        NONE, BALLOON, ONEAL, DALL, MINVO, DORIA, OVAPE, PONTAN, PASS
    }
}
