package raytracer.camera;

import raytracer.Scene;
import raytracer.ray.Ray;
import raytracer.shapes.properties.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
/**
 * The class Renderer for rendering the scene to a file
 */
public final class Renderer {
    private Renderer() {
        // Empty constructor, needed for export of functions
    }

/*    *//**
     * Renders a scene from the specified camera to a P3-format image file.
     *
     * @param scene    the scene to render
     * @param camera   the camera used to generate pixel rays
     * @param width    the image width in pixels
     * @param height   the image height in pixels
     * @param filename the output file path
     * @throws IOException if the output file cannot be written
     */
    public static void render(Scene scene, Camera camera, int width, int height, String filename) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Ray ray = camera.getRay(x, y);
                Color color = scene.trace(ray)
                        .map(h -> h.material().colorAt(h))
                        .orElse(new Color(0.1, 0.1, 0.2));

                int rgb = colorToArgb(color);
                image.setRGB(x, y, rgb);
            }
        }

        ImageIO.write(image, "png", new File(filename));
    }

    private static int colorToArgb(Color c) {
        int r = clamp((int) Math.round(c.r() * 255));
        int g = clamp((int) Math.round(c.g() * 255));
        int b = clamp((int) Math.round(c.b() * 255));
        return (0xFF << 24) | (r << 16) | (g << 8) | b; // sets alpha to 255, opaque
    }
    private static int clamp(int value) {
        return Math.clamp(value, 0, 255);
    }
}