package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.entities.AnimatedEntitiy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.character.Character;


public class FlameSegment extends AnimatedEntitiy {

	protected boolean _last;
	private int _direction;

	/**
	 *
	 * @param x
	 * @param y
	 * @param direction
	 * @param last cho biết segment này là cuối cùng của Flame hay không,
	 *                segment cuối có sprite khác so với các segment còn lại
	 */
	public FlameSegment(int x, int y, int direction, boolean last) {
		_x = x;
		_y = y;
		_last = last;
		_direction = direction;

		updateSprite();
	}
	
	@Override
	public void render(Screen screen) {
		int xt = (int)_x << 4;
		int yt = (int)_y << 4;

		screen.renderEntity(xt, yt , this);
	}
	
	@Override
	public void update()
	{
		animate();
		updateSprite();
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý khi FlameSegment va chạm với Character
		if (e instanceof Character) {
			((Character) e).kill();
			return false;
		}

		return true;
	}

	private void updateSprite()
	{
		switch (_direction) {
			case 0:
				if(!_last) {
					_sprite = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, _animate, 60);
				} else {
					_sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1, Sprite.explosion_vertical_top_last2, _animate, 60);
				}
				break;
			case 1:
				if(!_last) {
					_sprite = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, _animate, 60);
				} else {
					_sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, _animate, 60);
				}
				break;
			case 2:
				if(!_last) {
					_sprite = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, _animate, 60);

				} else {
					_sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, _animate, 60);
				}
				break;
			case 3:
				if(!_last) {
					_sprite = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, _animate, 60);
				} else {
					_sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1, Sprite.explosion_horizontal_left_last2, _animate, 60);
				}
				break;
		}
	}
}