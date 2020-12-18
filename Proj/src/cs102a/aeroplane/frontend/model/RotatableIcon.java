package cs102a.aeroplane.frontend.model;

import javax.swing.*;
import java.awt.*;

public class RotatableIcon extends ImageIcon implements Icon {

    private final Icon icon;
    private double degrees;

    /**
     * Create a RotatedIcon. The icon will rotate about its center. This
     * constructor will automatically set the Rotate enum to ABOUT_CENTER.
     *
     * @param iconPath the Icon to rotate
     * @param degrees  the degrees of rotation
     */
    public RotatableIcon(String iconPath, double degrees) {
        this.icon = new ImageIcon(iconPath);
        setDegrees(degrees);
    }

    public RotatableIcon(Icon icon, double degrees) {
        this.icon = icon;
        setDegrees(degrees);
    }


    /**
     * Set the degrees of rotation. Only used for Rotate.ABOUT_CENTER.
     * This method only sets the degree of rotation, it will not cause
     * the Icon to be repainted. You must invoke repaint() on any
     * component using this icon for it to be repainted.
     *
     * @param degrees the degrees of rotation
     */
    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    @Override
    public int getIconWidth() {
        double radians = Math.toRadians(degrees);
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        return (int) Math.floor(icon.getIconWidth() * cos + icon.getIconHeight() * sin);

    }

    @Override
    public int getIconHeight() {
        double radians = Math.toRadians(degrees);
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        return (int) Math.floor(icon.getIconHeight() * cos + icon.getIconWidth() * sin);

    }

    /**
     * Paint the icons of this compound icon at the specified location
     *
     * @param c The component on which the icon is painted
     * @param g the graphics context
     * @param x the X coordinate of the icon's top-left corner
     * @param y the Y coordinate of the icon's top-left corner
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();

        int cWidth = icon.getIconWidth() / 2;
        int cHeight = icon.getIconHeight() / 2;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setClip(x, y, getIconWidth(), getIconHeight());
        g2.translate((getIconWidth() - icon.getIconWidth()) / 2, (getIconHeight() - icon.getIconHeight()) / 2);
        g2.rotate(Math.toRadians(degrees), x + cWidth, y + cHeight);
        icon.paintIcon(c, g2, x, y);

        g2.dispose();
    }
}