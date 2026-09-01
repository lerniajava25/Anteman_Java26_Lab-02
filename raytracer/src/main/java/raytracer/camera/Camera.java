package raytracer.camera;

import raytracer.ray.Ray;
import raytracer.ray.Vector;

/**
 * The type Camera.
 */
public class Camera {
    private final int imageWidth;
    private final int imageHeight;
    private final double planeWidth;
    private final double planeHeight;
    private final Vector origin;

    public Camera(int imageWidth, int imageHeight, Vector origin) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        double aspectRatio = (double) imageWidth / imageHeight;
        this.planeHeight = 2.0;
        this.planeWidth = aspectRatio * planeHeight;
        this.origin = origin;
    }
    public Camera(int imageWidth, int imageHeight) {
        Vector origo = new Vector(0, 0, 0);
        this(imageWidth, imageHeight, origo);
    }

    public Ray getRay(int px, int py) {
        double u = (px + 0.5) / imageWidth;
        double v = (py + 0.5) / imageHeight;

        double x = (u - 0.5) * planeWidth;
        double y = (0.5 - v) * planeHeight;
        double z = -1.0; // camera is looking down the negative z-axis and plane is at z=-1.0

        Vector direction = new Vector(x, y, z).subtract(origin).normalize();
        return new Ray(origin, direction);
    }
}
