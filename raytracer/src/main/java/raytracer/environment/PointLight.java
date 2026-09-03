package raytracer.environment;

import raytracer.ray.Vector3D;
import raytracer.shapes.properties.Color;

/**
 * A point light source in 3D space that illuminates the scene.
 *
 * @param position  the 3D position of the light source
 * @param color     the color of the emitted light
 * @param intensity the intensity/brightness of the light (typically 0.0 to 1.0)
 */
public record PointLight(Vector3D position, Color color, double intensity) {}
