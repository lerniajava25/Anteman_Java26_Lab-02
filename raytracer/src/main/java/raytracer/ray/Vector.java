package raytracer.ray;

public record Vector(double x, double y, double z) {
    public Vector add(Vector o) {
        return new Vector(this.x + o.x, this.y + o.y, this.z + o.z);
    }
    public Vector subtract(Vector o) {
        return new Vector(this.x - o.x, this.y - o.y, this.z - o.z);
    }
    public Vector scale(double s) {
        return new Vector(this.x * s, this.y * s, this.z * s);
    }
    public double dotProduct(Vector o) {
        return this.x * o.x + this.y * o.y + this.z * o.z;
    }
    public Vector crossProduct(Vector o) {
        return new Vector(this.y * o.z - this.z * o.y, this.z * o.x - this.x * o.z, this.x * o.y - this.y * o.x);
    }
    public double length() {
        return Math.sqrt(dotProduct(this));
    }
    public Vector normalize() {
        double l = length();
        return new Vector(this.x / l, this.y / l, this.z / l);
    }
}