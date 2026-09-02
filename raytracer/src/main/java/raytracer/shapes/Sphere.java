package raytracer.shapes;

import raytracer.ray.HitRecord;
import raytracer.ray.Ray;
import raytracer.ray.Vector;
import raytracer.shapes.properties.Material;

import java.util.Optional;

public class Sphere implements Shape {
    private final Vector center;
    private final double radius;
    private final Material material;

    public Sphere(Vector center, double radius, Material material) {
        this.center = center;
        this.radius = radius;
        this.material = material;
    }

    @Override
    public Optional<HitRecord> hit(Ray ray) {
        Vector oc = ray.origin().subtract(center);
        double a = ray.direction().dotProduct(ray.direction());
        double b = 2.0 * ray.direction().dotProduct(oc);
        double c = oc.dotProduct(oc) - radius * radius;
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

        Vector point = ray.origin().add(ray.direction().scale(t));
        Vector normal = point.subtract(center).normalize();
        return Optional.of(new HitRecord(t, point, normal, material));
    }
}
