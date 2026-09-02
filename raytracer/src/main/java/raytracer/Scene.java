package raytracer;

import raytracer.ray.HitRecord;
import raytracer.ray.Ray;
import raytracer.shapes.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Scene {
    private final List<Shape> shapes = new ArrayList<>();

    public void add(Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Shape cannot be null");
        }

        shapes.add(shape);
    }

    public Optional<HitRecord> trace(Ray ray) {
        Optional<HitRecord> closestHit = Optional.empty();
        double closestT = Double.MAX_VALUE;

        for (Shape shape : shapes) {
            Optional<HitRecord> hit = shape.hit(ray);
            if (hit.isPresent() && hit.get().t() < closestT) {
                closestT = hit.get().t();
                closestHit = hit;
            }
        }
        return closestHit;
    }
}
