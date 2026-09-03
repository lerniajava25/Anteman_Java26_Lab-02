package raytracer.shapes.properties;

import raytracer.environment.PointLight;
import raytracer.ray.HitRecord;
import raytracer.ray.Vector3D;
/**
* Record Lambertian material
* Calculating how much light is reflected from the surface
* and how much is reflected from the light and mixing them
* @param baseColor
* */
public record Lambertian(Color baseColor) implements Material {
    /**
     * Computes the color at the hit point using Lambertian (diffuse) shading.
     * The color is calculated based on the angle between the surface normal and
     * the light direction, modulated by the base color, light color, and intensity.
     *
     * @param hit   the hit record containing the point and normal of the surface intersection
     * @param light the point light illuminating the surface
     * @return the computed color after applying diffuse lighting
     */
    @Override
    public Color colorAt(HitRecord hit, PointLight light) {
        Vector3D lightDir = light.position().subtract(hit.point()).normalize();
        double diffuse = Math.max(0.0, hit.normal().dotProduct(lightDir));

        double r = baseColor.r() * light.color().r() * diffuse * light.intensity();
        double g = baseColor.g() * light.color().g() * diffuse * light.intensity();
        double b = baseColor.b() * light.color().b() * diffuse * light.intensity();

        return new Color(r, g, b);
    }
}
