package cs102a.aeroplane.presets;

public enum Hangar {
    BLUE(0),
    GREEN(1),
    RED(2),
    YELLOW(3);

    private int id;

    Hangar(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
