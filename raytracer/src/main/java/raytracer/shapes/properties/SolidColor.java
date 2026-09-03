package raytracer.shapes.properties;

import raytracer.environment.PointLight;
import raytracer.ray.HitRecord;

public record SolidColor(Color color) implements Material {
    /**
     * Returns the solid color of this material, ignoring lighting and surface properties.
     * This provides a flat, unshaded appearance regardless of light position or intensity.
     *
     * @param hit   the hit record (unused for solid color materials)
     * @param light the point light (unused for solid color materials)
     * @return the solid color of this material
     */
    @Override
    public Color colorAt(HitRecord hit, PointLight light) {
        return color;
    }
}
