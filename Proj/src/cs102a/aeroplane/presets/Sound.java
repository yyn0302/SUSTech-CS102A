package cs102a.aeroplane.presets;

public enum Sound {
    ONE_STEP(0),
    JUMP(1),
    FLY(2),
    CRACK(3),
    FINISH(4),
    GAME_START(5),
    GAME_END(6);

    private int id;

    private Sound(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
