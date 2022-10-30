package uet.oop.bomberman.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Tiếp nhận và xử lý các sự kiện nhập từ bàn phím
 */
public class I_Keyboard implements KeyListener {
    private boolean[] keys = new boolean[999];
    public boolean up, down, left, right, space, backSpace, enter;

    public void update() {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W]; // lên
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S]; // xuống
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A]; // sang trái
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D]; // sang phải
        space = keys[KeyEvent.VK_SPACE] ; // thả bom
        backSpace = keys[KeyEvent.VK_BACK_SPACE]; // tạm dừng
        enter = keys[KeyEvent.VK_ENTER]; // tiếp tục
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

}
