package raytracer.shapes.properties;

import raytracer.ray.HitRecord;

public interface Material {
    Color colorAt(HitRecord hit);
}
