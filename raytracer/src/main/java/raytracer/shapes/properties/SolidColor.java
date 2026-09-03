package raytracer.shapes.properties;

import raytracer.environment.PointLight;
import raytracer.ray.HitRecord;

public record SolidColor(Color color) implements Material {
    @Override
    public Color colorAt(HitRecord hit, PointLight light) {
        return color;
    }
}
