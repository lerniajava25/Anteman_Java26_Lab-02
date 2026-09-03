package raytracer.shapes.properties;

import raytracer.environment.PointLight;
import raytracer.ray.HitRecord;

public interface Material {
    Color colorAt(HitRecord hit, PointLight light);
}
