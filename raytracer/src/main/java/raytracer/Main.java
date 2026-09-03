package raytracer;

import raytracer.camera.Camera;
import raytracer.camera.Renderer;
import raytracer.ray.Vector3D;
import raytracer.shapes.Sphere;
import raytracer.shapes.Triangle;
import raytracer.shapes.properties.Color;
import raytracer.shapes.properties.SolidColor;

import java.io.IOException;

class Main {
    /**
     * Renders the configured scene to {@code output.png}.
     *
     * @throws IOException if the rendered image cannot be written
     */
    static void main() throws IOException {
        int width = 400;
        int height = 300;
        Scene scene = new Scene();
        scene.add(new Sphere(new Vector3D(1, 0, 15), 0.8, new SolidColor(new Color(1, 0, 0))));
        scene.add(new Sphere(new Vector3D(-1, -1, 52), 1.5, new SolidColor(new Color(0, 1, 0))));
        scene.add(new Triangle(
                new Vector3D(0, 0, 30),
                new Vector3D(3, 0, 32),
                new Vector3D(1, 2, 31),
                new SolidColor(new Color(0, 0, 1))));
        Camera camera = new Camera(width, height);
        Renderer.render(scene, camera, width, height, "output.png");
    }
}
