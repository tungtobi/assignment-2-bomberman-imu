package uet.oop.bomberman.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Tiếp nhận và xử lý các sự kiện nhập từ bàn phím
 */
public class Keyboard implements KeyListener {
	
	private boolean[] keys = new boolean[120]; //120 is enough to this game
	public boolean up, down, left, right, space, pause;
	public boolean vk_a, vk_s, vk_d, vk_w, vk_j;

	public void update() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		space = keys[KeyEvent.VK_SPACE];
		pause = keys[KeyEvent.VK_P];

		vk_a = keys[KeyEvent.VK_A];
        vk_s = keys[KeyEvent.VK_S];
        vk_d = keys[KeyEvent.VK_D];
        vk_w = keys[KeyEvent.VK_W];
        vk_j = keys[KeyEvent.VK_J];
    }

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}
}
