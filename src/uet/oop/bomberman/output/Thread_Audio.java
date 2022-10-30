package uet.oop.bomberman.output;

import javazoom.jl.player.Player;

import java.io.FileInputStream;

public class Thread_Audio implements Runnable {
    private String PATH_AUDIO;
    private boolean loop;

    public Thread_Audio(String path, boolean loop) {
        this.PATH_AUDIO = path;
        this.loop = loop;
    }

    public Thread_Audio(String path) {
        this(path, false);
    }

    public void run() {
        FileInputStream fis;
        Player player;
        try {
            String path = Audio.class.getResource(PATH_AUDIO).getPath();
            do {
                fis = new FileInputStream(path);
                player = new Player(fis);
                player.play();
            } while (loop);
        } catch (Exception e) {
            System.out.println("Can't play audio.");
        }
    }
}
