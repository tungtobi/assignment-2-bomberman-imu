package uet.oop.bomberman.level;

import java.util.Scanner;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Dall;
import uet.oop.bomberman.entities.character.enemy.Doria;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.character.enemy.Minvo;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.character.enemy.Ovape;
import uet.oop.bomberman.entities.character.enemy.Pass;
import uet.oop.bomberman.entities.character.enemy.Pontan;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
	 * từ ma trận bản đồ trong tệp cấu hình
	 */
	private static char[][] _map;
	
	public FileLevelLoader(Board board, int level) throws LoadLevelException {
		super(board, level);
	}
	
	@Override
	public void loadLevel(int level) {
		// TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
		// TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
		String path = "/levels/Level" + String.valueOf(level) + ".txt"; 
		try
		{
			// ClassLoader classLoader = getClass().getClassLoader();
			InputStream in = getClass().getResourceAsStream(path);
			Scanner scanner = new Scanner(in, "UTF-8");

			String[] info = scanner.nextLine().split(" ");

			_level  = Integer.parseInt(info[0]);
			_height = Integer.parseInt(info[1]);
			_width  = Integer.parseInt(info[2]);

			_map = new char[_height][_width]; 
			for (int i = 0; i < _height; i++)
			{
				// TODO: check file format: throw an exception if line.hasNext() returns false
				String line = scanner.nextLine();

				for (int j = 0; j < _width; j++)
				{
					_map[i][j] = line.charAt(j);
				}
			}
		}
		catch (NullPointerException e)
		{
			System.err.println(e);
		}

		// DEBUG
		System.out.println("Test" + level);
		System.out.println(_level + " " + _height + " " + _width);
		for (int i = 0; i < _height; i++)
		{
			for (int j = 0; j < _width; j++)
			{
				System.out.print(_map[i][j]);
			}
			System.out.println();
		}
		// END DEBUG
	}

	@Override
	public void createEntities() {
		// TODO: tạo các Entity của màn chơi
		// TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

		// TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
		// TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình

		for (int i = 0; i < _height; i++)
		{
			for (int j = 0; j < _width; j++)
			{	
				switch (_map[i][j]) 
				{
					case '#':
						// thêm Wall
						_board.addEntity(i * _width + j, new Wall(j, i, Sprite.wall));
						break;
					case '*':
						// thêm Brick
						int xB = j, yB = i;
						_board.addEntity(xB + yB * _width,
								new LayeredEntity(xB, yB,
									new Grass(xB, yB, Sprite.grass),
									new Brick(xB, yB, Sprite.brick)
								)
						);
						break;
					case 'x':
						// thêm Portal kèm Brick che phủ ở trên
						int xP = j, yP = i;
						_board.addEntity(xP + yP * _width,
								new LayeredEntity(xP, yP,
									new Grass(xP ,yP, Sprite.grass),
									new Portal(xP, yP, Sprite.portal, _board),
									new Brick(xP, yP, Sprite.brick)
								)
						);
						break;
					case 'p':
						// thêm Bomber
						int xBomber = j, yBomber = i;
					
						_board.addCharacter( new Bomber(Coordinates.tileToPixel(xBomber), Coordinates.tileToPixel(yBomber) + Game.TILES_SIZE, _board) );
						Screen.setOffset(0, 0);
						_board.addEntity(xBomber + yBomber * _width, new Grass(xBomber, yBomber, Sprite.grass));
						break;
					case '1':
						// thêm Enemy
						int xBalloon = j, yBalloon = i;
						_board.addCharacter( new Balloon(Coordinates.tileToPixel(xBalloon), Coordinates.tileToPixel(yBalloon) + Game.TILES_SIZE, _board));
						_board.addEntity(xBalloon + yBalloon * _width, new Grass(xBalloon, yBalloon, Sprite.grass));
						break;
					case '2':
						// thêm Enemy
						int xOneal = j, yOneal = i;
						_board.addCharacter( new Oneal(Coordinates.tileToPixel(xOneal), Coordinates.tileToPixel(yOneal) + Game.TILES_SIZE, _board));
						_board.addEntity(xOneal + yOneal * _width, new Grass(xOneal, yOneal, Sprite.grass));
						break;
					case '3':
						// thêm Enemy
						int xDall = j, yDall = i;
						_board.addCharacter( new Dall(Coordinates.tileToPixel(xDall), Coordinates.tileToPixel(yDall) + Game.TILES_SIZE, _board));
						_board.addEntity(xDall + yDall * _width, new Grass(xDall, yDall, Sprite.grass));
						break;
					case '4':
						// thêm Enemy
						int xMinvo = j, yMinvo = i;
						_board.addCharacter( new Minvo(Coordinates.tileToPixel(xMinvo), Coordinates.tileToPixel(yMinvo) + Game.TILES_SIZE, _board));
						_board.addEntity(xMinvo + yMinvo * _width, new Grass(xMinvo, yMinvo, Sprite.grass));
						break;
					case '5':
						// thêm Enemy
						int xDoria = j, yDoria = i;
						_board.addCharacter( new Doria(Coordinates.tileToPixel(xDoria), Coordinates.tileToPixel(yDoria) + Game.TILES_SIZE, _board));
						_board.addEntity(xDoria + yDoria * _width, new Grass(xDoria, yDoria, Sprite.grass));
						break;
					case '6':
						// thêm Enemy
						int xOvape = j, yOvape = i;
						_board.addCharacter( new Ovape(Coordinates.tileToPixel(xOvape), Coordinates.tileToPixel(yOvape) + Game.TILES_SIZE, _board));
						_board.addEntity(xOvape + yOvape * _width, new Grass(xOvape, yOvape, Sprite.grass));
						break;
					case '7':
						// thêm Enemy
						int xPontan = j, yPontan = i;
						_board.addCharacter( new Pontan(Coordinates.tileToPixel(xPontan), Coordinates.tileToPixel(yPontan) + Game.TILES_SIZE, _board));
						_board.addEntity(xPontan + yPontan * _width, new Grass(xPontan, yPontan, Sprite.grass));
						break;
					case '8':
						// thêm Enemy
						int xPass = j, yPass = i;
						_board.addCharacter( new Pass(Coordinates.tileToPixel(xPass), Coordinates.tileToPixel(yPass) + Game.TILES_SIZE, _board));
						_board.addEntity(xPass + yPass * _width, new Grass(xPass, yPass, Sprite.grass));
						break;
					case 'b':
						// thêm Bomb Item kèm Brick che phủ ở trên
						int xBombI = j, yBombI = i;
						_board.addEntity(xBombI + yBombI * _width,
								new LayeredEntity(xBombI, yBombI,
									new Grass(xBombI, yBombI, Sprite.grass),
									new BombItem(xBombI, yBombI, Sprite.powerup_bombs),
									new Brick(xBombI, yBombI, Sprite.brick)
								)
						);
						break;
					case 'f':
						// thêm Flame Item kèm Brick che phủ ở trên
						int xFlameI = j, yFlameI = i;
						_board.addEntity(xFlameI + yFlameI * _width,
								new LayeredEntity(xFlameI, yFlameI,
									new Grass(xFlameI, yFlameI, Sprite.grass),
									new FlameItem(xFlameI, yFlameI, Sprite.powerup_flames),
									new Brick(xFlameI, yFlameI, Sprite.brick)
								)
						);
						break;
					case 's':
						// thêm Speed Item kèm Brick che phủ ở trên
						int xSpeedI = j, ySpeedI = i;
						_board.addEntity(xSpeedI + ySpeedI * _width,
								new LayeredEntity(xSpeedI, ySpeedI,
									new Grass(xSpeedI, ySpeedI, Sprite.grass),
									new SpeedItem(xSpeedI, ySpeedI, Sprite.powerup_speed),
									new Brick(xSpeedI, ySpeedI, Sprite.brick)
								)
						);
						break;
					default:
						_board.addEntity(i * _width + j, new Grass(j, i, Sprite.grass));
				}
			}
		}
	}

}
