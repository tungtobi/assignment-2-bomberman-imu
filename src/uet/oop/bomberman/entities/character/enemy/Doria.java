package uet.oop.bomberman.entities.character.enemy;


import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.enemy.ai.AIMedium;
import uet.oop.bomberman.entities.character.movement.Direction;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.graphics.Sprite;

public class Doria extends SmartEnemy {

	public Doria(int x, int y, Board board) {
		super(x, y, board, Sprite.kondoria_dead, Game.getEnemySpeed() / 3, 200);
		
		_sprite = Sprite.kondoria_left1;
	}

    @Override
    public boolean collide(Entity e) {
        if (e instanceof LayeredEntity)
            return true;

        return super.collide(e);
    }

    @Override
	protected void chooseSprite() {
		switch(_direction) {
			case UP:
			case RIGHT:
				if(_moving)
					_sprite = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, _animate, 60);
				else
					_sprite = Sprite.kondoria_left1;
				break;
            case DOWN:
            case LEFT:
				if(_moving)
					_sprite = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, _animate, 60);
				else
					_sprite = Sprite.kondoria_left1;
				break;
		}
	}
}
