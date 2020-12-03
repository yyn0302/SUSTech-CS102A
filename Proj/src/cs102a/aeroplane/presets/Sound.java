package cs102a.aeroplane.presets;

import java.io.File;

import cs102a.aeroplane.savegame.SystemSelect;

public class Sound {
    private File musicFile;
    private static String musicPath = SystemSelect.isMacOS() ?
            SystemSelect.getMacMusicPath() : SystemSelect.getWindowsMusicPath();

    public final static Sound ONE_STEP = new Sound();
    public final static Sound JUMP = new Sound();
    public final static Sound JET = new Sound();
    public final static Sound CRACK = new Sound();
    public final static Sound FINISH = new Sound();
    public final static Sound GAME_READY = new Sound(new File(String.format("%sutil_start_game.wav",musicPath)));
    public final static Sound GAMING = new Sound();
    public final static Sound GAME_END = new Sound();


    Sound(File musicFile) {
        this.musicFile = musicFile;
    }

    public File getMusicFile() {
        return musicFile;
    }

}
