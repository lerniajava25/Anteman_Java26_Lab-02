import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import raytracer.*;
import raytracer.ray.HitRecord;
import raytracer.ray.Ray;
import raytracer.ray.Vector;
import raytracer.shapes.properties.Color;
import raytracer.shapes.properties.SolidColor;
import raytracer.shapes.Sphere;

import java.util.Optional;

class SceneTest {
    @Test
    void rayHittingSphereReturnsHit() {
        Scene scene = new Scene();
        scene.add(new Sphere(
                new Vector(0,0,-5),
                1.0,
                new SolidColor(new Color(1,0,0))
        ));

        Ray ray = new Ray(new Vector(0,0,0), new Vector(0,0,-1));
        Optional<HitRecord> hit = scene.trace(ray);

        assertTrue(hit.isPresent());
        assertEquals(4.0, hit.get().t(), 0.0001);

    }
    @Test
    void rayMissesSphereReturnsEmpty() {
        Scene scene = new Scene();
        scene.add(new Sphere(
                new Vector(0,0,-5),
                1.0,
                new SolidColor(new Color(1,0,0))
        ));

        Ray ray = new Ray(new Vector(5,0,0), new Vector(0,0,-1));
        Optional<HitRecord> hit = scene.trace(ray);

        assertFalse(hit.isPresent());
    }
}
