package raytracer.shapes.properties;

/**
 * The type Color.
 */
public record Color(double r, double g, double b) {
    /**
     * Scale color.
     *
     * @param s the s
     * @return the color
     */
    public Color scale(double s) {
        return new Color(r * s, g * s, b * s);
    }

    /**
     * Add color.
     *
     * @param c the c
     * @return the color
     */
    public Color add(Color c) {
        return new Color(r + c.r, g + c.g, b + c.b);
    }
}
