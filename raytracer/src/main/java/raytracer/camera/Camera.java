package raytracer.camera;

import raytracer.ray.Ray;
import raytracer.ray.Vector3D;

/**
 * The type Camera.
 */
public class Camera {
    private final int imageWidth;
    private final int imageHeight;
    private final double planeWidth;
    private final double planeHeight;
    private final Vector3D origin;

    public Camera(int imageWidth, int imageHeight, Vector3D origin) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        if (imageWidth <= 0 || imageHeight <= 0) {
            throw new IllegalArgumentException("Image dimensions must be positive");
        }
        double aspectRatio = (double) imageWidth / imageHeight;
        this.planeHeight = 2.0;
        this.planeWidth = aspectRatio * planeHeight;
        this.origin = origin;
    }
    public Camera(int imageWidth, int imageHeight) {
        this(imageWidth, imageHeight, new Vector3D(0, 0, 0));
    }

    public Ray getRay(int px, int py) {
        double u = (px + 0.5) / imageWidth;
        double v = (py + 0.5) / imageHeight;

        double x = (u - 0.5) * planeWidth;
        double y = (0.5 - v) * planeHeight;
        double z = -1.0; // camera is looking down the negative z-axis and plane is at z=-1.0

        Vector3D direction = new Vector3D(x, y, z).subtract(origin).normalize();
        return new Ray(origin, direction);
    }
}
