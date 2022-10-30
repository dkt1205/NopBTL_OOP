package uet.oop.bomberman.output;

public class Audio {
    private static String PATH_EXPLOSION = "/audios/Explosion.mp3";
    private static String PATH_BACKGROUND = "/audios/background_music.mp3";
    private static String PATH_WAIT = "/audios/start.mp3";
    private static String PATH_GAME_OVER = "/audios/game_over.mp3";
    private static String PATH_DEATH = "/audios/death.mp3";
    private static String PATH_EAT = "/audios/eat.mp3";
    private static String PATH_WIN ="/audios/win.mp3";
    private static String PATH_KILL ="/audios/kill.mp3";

    private static Thread threadKill;
    private static Thread threadWin;
    private static Thread threadExplosion;
    private static Thread threadBackground;
    private static Thread threadWait;
    private static Thread threadGameOver;
    private static Thread threadDeath;
    private static Thread threadEat;

    public static void playWin(){
        stopAudioBackground(threadWin);
        if(threadWin==null ||!threadWin.isAlive()) {
            threadWin = new Thread(new Thread_Audio(PATH_WIN));
            threadWin.start();
        }
    }

    public static void playEat() {
        threadEat = new Thread(new Thread_Audio(PATH_EAT));
        threadEat.start();
    }
    public static void playExplosion() {
        threadExplosion = new Thread(new Thread_Audio(PATH_EXPLOSION));
        threadExplosion.start();
    }
    public static void playKill() {
        threadKill = new Thread(new Thread_Audio(PATH_KILL));
        threadKill.start();
    }

    public static void playDeath() {
        threadDeath = new Thread(new Thread_Audio(PATH_DEATH));
        threadDeath.start();
    }

    public static void playBackground() {
        stopAudioBackground(threadBackground);
        if (threadBackground == null || !threadBackground.isAlive()) {
            threadBackground = new Thread(new Thread_Audio(PATH_BACKGROUND, true));
            threadBackground.start();
        } else {
            threadBackground.resume();
        }
    }

    public static void playWait() {
        stopAudioBackground(threadWait);
        if (threadWait == null || !threadWait.isAlive()) {
            threadWait = new Thread(new Thread_Audio(PATH_WAIT));
            threadWait.start();
        }
    }

    public static void playGameOver() {
        stopAudioBackground(threadGameOver);
        if (threadGameOver == null || !threadGameOver.isAlive()) {
            threadGameOver = new Thread(new Thread_Audio(PATH_GAME_OVER));
            threadGameOver.start();
        }
    }

    private static boolean hasThread(Thread thread, Thread[] threads) {
        for (Thread temp : threads) {
            if (thread == temp) return true;
        }
        return false;
    }

    public static void stopAudioBackground(Thread... notStop) {
        if (threadWait != null && threadWait.isAlive() && !hasThread(threadWait, notStop))
            threadWait.stop();
        if (threadBackground != null && threadBackground.isAlive() && !hasThread(threadBackground, notStop))
            threadBackground.stop();
        if (threadGameOver != null && threadGameOver.isAlive() && !hasThread(threadGameOver, notStop))
            threadGameOver.stop();
    }

    public static void stopAudioEffect(Thread... notStop) {
        if (threadDeath != null && !threadDeath.isAlive())
            threadDeath.stop();
        if (threadExplosion != null && !threadExplosion.isAlive())
            threadExplosion.stop();
    }

    public static void suspendAudioEffect(Thread... notSuspend) {
        if (threadDeath != null)
            threadDeath.suspend();
        if (threadExplosion != null)
            threadExplosion.suspend();
    }

    public static void resumeAudioEffect(Thread... notSuspend) {
        if (threadDeath != null)
            threadDeath.resume();
        if (threadExplosion != null)
            threadExplosion.resume();
    }

    public static void stopAllAudio(Thread... notStop) {
        stopAudioBackground(notStop);
        stopAudioEffect(notStop);
    }
}
