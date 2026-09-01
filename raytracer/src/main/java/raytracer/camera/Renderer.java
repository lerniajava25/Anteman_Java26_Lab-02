package raytracer.camera;

import raytracer.Scene;
import raytracer.ray.HitRecord;
import raytracer.ray.Ray;
import raytracer.shapes.properties.Color;

import java.io.*;
import java.util.Optional;

/**
 * The class Renderer for rendering the scene to a file
 */
public class Renderer {
    public Renderer() {
        // Empty constructor, needed for export of functions
    }

    public static void render(Scene scene, Camera camera, int width, int height, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("P3");
            writer.println(width + " " + height);
            writer.println("255");

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Ray ray = camera.getRay(x, y);
                    Optional<HitRecord> hit = scene.trace(ray);

                    Color color = hit
                            .map(h -> h.material().colorAt(h))
                            .orElse(new Color(0.1, 0.1, 0.2)); // background color

                    writer.println(toPixel(color));
                }
            }
        }
    }

    private static String toPixel(Color c) {
        int r = clamp((int) (c.r() * 255));
        int g = clamp((int) (c.g() * 255));
        int b = clamp((int) (c.b() * 255));
        return r + " " + g + " " + b;
    }

    private static int clamp(int value) {
        return Math.clamp(value, 0, 255);
    }
}