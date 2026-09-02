package raytracer.shapes;

import raytracer.ray.HitRecord;
import raytracer.ray.Ray;
import raytracer.ray.Vector3D;
import raytracer.shapes.properties.Material;

import java.util.Optional;

public class Triangle implements Shape {
    private final Vector3D v0;
    private final Vector3D v1;
    private final Vector3D v2;
    private final Material material;
    private static final double EPSILON = 0.0000001;

    public Triangle(Vector3D v0, Vector3D v1, Vector3D v2, Material material) {
        if (v0 == null || v1 == null || v2 == null) {
            throw new IllegalArgumentException("Vertices cannot be null");
        }
        if (!v0.isFinite() || !v1.isFinite() || !v2.isFinite()) {
            throw new IllegalArgumentException("Vertices must be finite");
        }
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
        this.material = material;
    }

    @Override
    public Optional<HitRecord> hit(Ray ray) {
        Vector3D edge1 = v1.subtract(v0);
        Vector3D edge2 = v2.subtract(v0);
        Vector3D h = ray.direction().crossProduct(edge2);
        double a = edge1.dotProduct(h);

        if (Math.abs(a) < EPSILON) {
            return Optional.empty();
        }

        double f = 1.0 / a;
        Vector3D s = ray.origin().subtract(v0);
        double u = f * s.dotProduct(h);

        if (u < 0.0 || u > 1.0) {
            return Optional.empty();
        }

        Vector3D q = s.crossProduct(edge1);
        double v = f * ray.direction().dotProduct(q);

        if (v < 0.0 || u + v > 1.0) {
            return Optional.empty();
        }

        double t = f * edge2.dotProduct(q);

        if (t < 0.001) {
            return Optional.empty();
        }

        Vector3D point = ray.origin().add(ray.direction().scale(t));
        Vector3D normal = edge1.crossProduct(edge2).normalize();
        return Optional.of(new HitRecord(t, point, normal, material));
    }
}
