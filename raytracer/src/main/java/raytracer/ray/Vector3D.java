package raytracer.ray;

public record Vector3D(double x, double y, double z) {
    public Vector3D add(Vector3D o) {
        return new Vector3D(this.x + o.x, this.y + o.y, this.z + o.z);
    }
    public Vector3D subtract(Vector3D o) {
        return new Vector3D(this.x - o.x, this.y - o.y, this.z - o.z);
    }
    public Vector3D scale(double s) {
        return new Vector3D(this.x * s, this.y * s, this.z * s);
    }
    public double dotProduct(Vector3D o) {
        return this.x * o.x + this.y * o.y + this.z * o.z;
    }
    public Vector3D crossProduct(Vector3D o) {
        return new Vector3D(this.y * o.z - this.z * o.y, this.z * o.x - this.x * o.z, this.x * o.y - this.y * o.x);
    }
    public double length() {
        return Math.sqrt(dotProduct(this));
    }
    public Vector3D normalize() {
        double l = length();
        return new Vector3D(this.x / l, this.y / l, this.z / l);
    }
    public boolean coordinatesDefined() {
        return !Double.isNaN(this.x) && !Double.isNaN(this.y) && !Double.isNaN(this.z);
    }
    public boolean isFinite() {
        return Double.isFinite(this.x) && Double.isFinite(this.y) && Double.isFinite(this.z);
    }
    public boolean isZero() {
        return x == 0 && y == 0 && z == 0;
    }
}