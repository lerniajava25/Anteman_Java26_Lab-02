package raytracer.shapes.properties;

import raytracer.environment.PointLight;
import raytracer.ray.HitRecord;

public interface Material {
    /**
     * Computes the color at a surface intersection point based on the material properties
     * and lighting conditions.
     *
     * @param hit   the hit record containing the point and normal of the surface intersection
     * @param light the point light illuminating the surface
     * @return the computed color at the hit point
     */
    Color colorAt(HitRecord hit, PointLight light);
}
