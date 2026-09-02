package raytracer.shapes;

import raytracer.ray.HitRecord;
import raytracer.ray.Ray;
import raytracer.ray.Vector3D;
import raytracer.shapes.properties.Material;

import java.util.Optional;

public class Sphere implements Shape {
    private final Vector3D center;
    private final double radius;
    private final Material material;

    public Sphere(Vector3D center, double radius, Material material) {
        if (center == null) {
            throw new IllegalArgumentException("Center cannot be null");
        }
        if (!center.coordinatesDefined()) {
            throw new IllegalArgumentException("Center coordinates must be defined");
        }
        if (!center.isFinite()) {
            throw new IllegalArgumentException("Center must be finite");
        }
        if (radius == null) {
            throw new IllegalArgumentException("Radius cannot be null");
        }
        if (!Double.isFinite(radius) || radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive and finite");
        }
        if (Double.NaN(radius)) {
            throw new IllegalArgumentException("Radius must be a number");
        }
        if (material == null) {
            throw new IllegalArgumentException("Material cannot be null");
        }
        this.center = center;
        this.radius = radius;
        this.material = material;
    }

    public Optional<HitRecord> hit(Ray ray) {
        Vector3D oc = ray.origin().subtract(this.center);
        if (ray.direction().equals(new Vector3D(0,0,0))) {
            throw new IllegalArgumentException("Ray direction cannot be zero");
        }
        double a = ray.direction().dotProduct(ray.direction());
        double b = 2.0 * ray.direction().dotProduct(oc);
        double c = oc.dotProduct(oc) - radius * radius; // Getting |oc|² - r² to avoid sqrt
        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) {
            return Optional.empty();
        }

        double sqrtD =  Math.sqrt(discriminant);
        double t1 = (-b - sqrtD) / (2 * a);
        double t2 = (-b + sqrtD) / (2 * a);

        double t = (t1 > 0.001) ? t1 : t2;
        if (t < 0.001) {
            return Optional.empty();
        }

        Vector3D point = ray.origin().add(ray.direction().scale(t));
        Vector3D normal = point.subtract(center).normalize();
        return Optional.of(new HitRecord(t, point, normal, material));
    }
}
