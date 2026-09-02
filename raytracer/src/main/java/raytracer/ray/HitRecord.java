package raytracer.ray;

import raytracer.shapes.properties.Material;

public record HitRecord(double t, Vector point, Vector normal, Material material) {}
