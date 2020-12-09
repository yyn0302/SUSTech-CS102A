package cs102a.aeroplane.presets;

import java.io.File;

import cs102a.aeroplane.util.SystemSelect;

public class Sound {
    private File musicFile;
    private static String musicPath = SystemSelect.isMacOS() ?
            SystemSelect.getMacMusicPath() : SystemSelect.getWindowsMusicPath();

    public final static Sound ONE_STEP = new Sound(new File(String.format("%sNormMove.wav",musicPath)));
    public final static Sound JUMP = new Sound(new File(String.format("%sJump.wav",musicPath)));
    public final static Sound JET = new Sound(new File(String.format("%sFly.wav",musicPath)));
    public final static Sound CRACK = new Sound(new File(String.format("%sCrack.wav",musicPath)));
    public final static Sound FINISH_ONE_PLANE = new Sound(new File(String.format("%sFinish.wav",musicPath)));
//    public final static Sound GAME_READY = new Sound(new File(String.format("%sutil_start_game.wav",musicPath)));
    public final static Sound GAMING_THEME1 = new Sound(new File(String.format("%sBGM_Auamen.wav",musicPath)));
    public final static Sound GAMING_THEME2 = new Sound(new File(String.format("%sBGM_Incarnation.wav",musicPath)));
//    public final static Sound GAME_END = new Sound(new File(String.format("%sutil_start_game.wav",musicPath)));


    Sound(File musicFile) {
        this.musicFile = musicFile;
    }

    public File getMusicFile() {
        return musicFile;
    }

}
