package cs102a.aeroplane.presets;

import cs102a.aeroplane.util.MusicPlayer;
import cs102a.aeroplane.util.SystemSelect;

import java.io.File;

public class Sound {
    private final File musicFile;
    private static final String musicPath = SystemSelect.getMusicPath();
    MusicPlayer player = new MusicPlayer(this);

    public final static Sound ONE_STEP = new Sound(new File(String.format("%sNormMove.wav", musicPath)));
    public final static Sound JUMP = new Sound(new File(String.format("%sJump.wav", musicPath)));
    public final static Sound JET = new Sound(new File(String.format("%sFly.wav", musicPath)));
    public final static Sound CRACK = new Sound(new File(String.format("%sCrack.wav", musicPath)));
    public final static Sound FINISH_ONE_PLANE = new Sound(new File(String.format("%sFinish.wav", musicPath)));
    public final static Sound GAMING_THEME1 = new Sound(new File(String.format("%sBGM_Auamen.wav", musicPath)));
    public final static Sound GAMING_THEME2 = new Sound(new File(String.format("%sBGM_Incarnation.wav", musicPath)));


    Sound(File musicFile) {
        this.musicFile = musicFile;
    }

    public File getMusicFile() {
        return musicFile;
    }

    public final void play(boolean isLoop) {
        player.setVolume(6);
        player.setLoop(isLoop);
        player.play();
        if (!isLoop) System.gc();
    }

    // 结束音乐
    public final void over() {
        player.over();
    }

    // 静音按键调用，设置bgm静音
    public final void changeIsMute() {
        if (player.getVolume() == 6) player.setVolume(-80);
        else player.setVolume(6);
    }

}
