package raytracer.environment;

import raytracer.ray.Vector3D;
import raytracer.shapes.properties.Color;

public record PointLight(Vector3D position, Color color, double intensity) {}
