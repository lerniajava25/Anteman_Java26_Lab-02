
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import raytracer.*;
import raytracer.ray.HitRecord;
import raytracer.ray.Ray;
import raytracer.ray.Vector3D;
import raytracer.shapes.Triangle;
import raytracer.shapes.properties.Color;
import raytracer.shapes.properties.SolidColor;
import raytracer.shapes.Sphere;

import java.util.Optional;

class SceneTest {
    @Test
    void rayHittingSphereReturnsHit() {
        Scene scene = new Scene();
        scene.add(new Sphere(
                new Vector3D(0,0,-5),
                1.0,
                new SolidColor(new Color(1,0,0))
        ));

        Ray ray = new Ray(new Vector3D(0,0,0), new Vector3D(0,0,-1));
        Optional<HitRecord> hit = scene.trace(ray);

        assertTrue(hit.isPresent());
        assertEquals(4.0, hit.get().t(), 0.0001);

    }
    @Test
    void rayMissesSphereReturnsEmpty() {
        Scene scene = new Scene();
        scene.add(new Sphere(
                new Vector3D(0,0,-5),
                1.0,
                new SolidColor(new Color(1,0,0))
        ));

        Ray ray = new Ray(new Vector3D(5,0,0), new Vector3D(0,0,-1));
        Optional<HitRecord> hit = scene.trace(ray);

        assertFalse(hit.isPresent());
    }

    @Test
    void rayHitsTriangleReturnsHit() {
        Scene scene = new Scene();
        scene.add(new Triangle(
                new Vector3D(0, 1, -4),
                new Vector3D(-1, -1, -4),
                new Vector3D(1, -1, -4),
                new SolidColor(new Color(0, 0, 1)
                )));
        Ray ray = new Ray(new Vector3D(0,0,0), new Vector3D(0,0,-1));
        Optional<HitRecord> hit = scene.trace(ray);

        assertTrue(hit.isPresent());
        assertEquals(4.0, hit.get().t(), 0.0001);
    }

    @Test
    void rayMissesTriangleReturnsEmpty() {
        Scene scene = new Scene();
        scene.add(new Triangle(
                new Vector3D(0, 1, -4),
                new Vector3D(-1, -1, -4),
                new Vector3D(1, -1, -4),
                new SolidColor(new Color(0, 0, 1)
                )));

        Ray ray = new Ray(new Vector3D(0,0,0), new Vector3D(0,0,1));
        Optional<HitRecord> hit = scene.trace(ray);

        assertFalse(hit.isPresent());
    }

    @Test
    void rayParallelToTriangleReturnsEmpty() {
        Triangle triangle = new Triangle( // Triangle with a normal pointing up and plane parallel to the x-axis
                new Vector3D(0, 1, -5),
                new Vector3D(-1, -1, -5),
                new Vector3D(1, -1, -5),
                new SolidColor(new Color(0, 1, 0))
        );

        Ray ray = new Ray(new Vector3D(0, 0, -5), new Vector3D(1, 0, 0)); // Ray along the x-axis
        Optional<HitRecord> hit = triangle.hit(ray);

        assertFalse(hit.isPresent());
    }
}
