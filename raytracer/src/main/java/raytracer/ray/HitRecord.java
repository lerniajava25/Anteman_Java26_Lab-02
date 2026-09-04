package raytracer.ray;

import raytracer.shapes.properties.Material;

public record HitRecord(double t,
                        Vector3D point,
                        Vector3D normal,
                        Material material) {

}
