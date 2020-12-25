package Ex11;

import java.awt.*;

public enum ShapeColor {

    GREEN(" The shape is in the Screen", Color.GREEN),
    RED(" The shape is not in the Screen", Color.RED),
    GRAY(" Haven't tested", Color.GRAY);

    private String desc;
    private Color color;

    ShapeColor(String desc, Color color) {
        this.desc = desc;
        this.color = color;
    }

    public String getDesc() {
        return this.desc;
    }

    public Color getColor() {
        return this.color;
    }

}
