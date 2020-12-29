package Ex11;

public class Rectangle {
    private double x;
    private double y;
    private double width;
    private double height;
    private static int screenSize = 10;
    private ShapeColor color = ShapeColor.GRAY;

    public Rectangle(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public static int getScreenSize() {
        return screenSize;
    }

    public static void setScreenSize(int screenSize) {
        Rectangle.screenSize = screenSize;
    }

    public void checkColor() {
        if (isInBoundary()) {
            color = ShapeColor.GREEN;
        } else {
            color = ShapeColor.RED;
        }
    }

    public boolean isInBoundary() {
        if (-1 * Rectangle.screenSize > this.x - this.width / 2 ||
                Rectangle.screenSize < this.x + this.width / 2) {
            return false;
        }

        if (-1 * Rectangle.screenSize > this.y - this.height / 2 ||
                Rectangle.screenSize < this.y + this.height / 2) {
            return false;
        }
        return true;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String toString() {
        return " Rectangle{" + " width=" + width + " , height=" + height + " x=" + x + " , y=" + y + " , color=" + color + " }\n";
    }

    public void draw() {
        StdDraw.setPenColor(color.getColor());
        StdDraw.filledRectangle(x, y, this.width / 2, this.height / 2);
    }
}