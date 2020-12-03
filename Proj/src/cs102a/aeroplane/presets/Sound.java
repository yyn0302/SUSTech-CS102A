package cs102a.aeroplane.presets;

public enum Sound {
    ONE_STEP(0),
    JUMP(1),
    JET(2),
    CRACK(3),
    FINISH(4),
    GAME_START(5),
    GAME_END(6);

    private int soundID;

    private Sound(int soundID) {
        this.soundID = soundID;
    }

    public int getSoundID() {
        return soundID;
    }
}
