package raytracer.shapes;

import raytracer.ray.HitRecord;
import raytracer.ray.Ray;

import java.util.Optional;

public interface Shape {
    Optional<HitRecord> hit (Ray ray);
}
