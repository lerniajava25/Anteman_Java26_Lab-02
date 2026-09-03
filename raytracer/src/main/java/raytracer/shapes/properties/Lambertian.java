package raytracer.shapes.properties;

import raytracer.ray.HitRecord;

public class Lambertian implements Material {
    private final Color albedo; // Color/grade of reflection (0.0-1.0)

    public Lambertian(Color albedo) {
        this.albedo = albedo;
    }

    @Override
    public Color colorAt(HitRecord hit) {
        /*
        * In here we would calculate the effect of light on the material. (If we had a light source)
        * */
        return albedo;
    }
}
