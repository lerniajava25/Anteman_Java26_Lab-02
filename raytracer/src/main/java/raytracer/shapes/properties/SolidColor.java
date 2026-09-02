package raytracer.shapes.properties;

import raytracer.ray.HitRecord;

public record SolidColor(Color color) implements Material {
    @Override
    public Color colorAt(HitRecord hit) {
        return color;
    }
}
