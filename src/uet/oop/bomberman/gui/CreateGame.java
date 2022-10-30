package uet.oop.bomberman.gui;

import  uet.oop.bomberman.GameLoop;

import javax.swing.*;
import java.awt.*;

public class CreateGame extends JFrame {

    public Game_Panel _gamepane;
    private final JPanel _containerpane;
    private final Info_Panel _infopanel;

    private final GameLoop _game;

    public CreateGame() {
        _containerpane = new JPanel(new BorderLayout());
        _gamepane = new Game_Panel(this);
        _infopanel = new Info_Panel(_gamepane.getGame());
        _containerpane.add(_infopanel, BorderLayout.PAGE_START);
        _containerpane.add(_gamepane, BorderLayout.PAGE_END);
        _game = _gamepane.getGame();
        add(_containerpane);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        _game.start();
    }

    public void setTime(int time) {
        _infopanel.setTime(time);
    }

    public void setPoints(int points) {
        _infopanel.setPoints(points);
    }

}
