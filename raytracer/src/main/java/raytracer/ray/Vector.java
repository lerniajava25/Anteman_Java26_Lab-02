package raytracer.ray;

public record Vector(double x, double y, double z) {
    public Vector add(Vector o) {
        return new Vector(x + o.x, y + o.y, z + o.z);
    }
    public Vector subtract(Vector o) {
        return new Vector(x - o.x, y - o.y, z - o.z);
    }
    public Vector scale(double s) {
        return new Vector(x * s, y * s, z * s);
    }
    public double dotProduct(Vector o) {
        return x * o.x + y * o.y + z * o.z;
    }
    public Vector crossProduct(Vector o) {
        return new Vector(y * o.z - z * o.y, z * o.x - x * o.z, x * o.y - y * o.x);
    }
    public double length() {
        return Math.sqrt(dotProduct(this));
    }
    public Vector normalize() {
        double l = length();
        return new Vector(x / l, y / l, z / l);
    }
}