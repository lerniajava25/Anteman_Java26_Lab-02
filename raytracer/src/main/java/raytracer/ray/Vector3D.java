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
    /**
     * Computes the Euclidean length of this vector.
     *
     * @return the vector's Euclidean length
     */
    public double length() {
        return Math.sqrt(dotProduct(this));
    }
    /**
     * Produces a unit-length vector with the same direction as this vector.
     *
     * @return the normalized vector
     * @throws ArithmeticException if this vector's length is zero or negative
     */
    public Vector3D normalize() {
        double l = this.length();

        if (Double.isNaN(l)) {
            throw new ArithmeticException("Cannot normalize a NaN vector");
        }
        if (!Double.isFinite(l) || l <= 0) {
            throw new ArithmeticException("Cannot normalize a zero, infinite or negative length vector");
        }

        return new Vector3D(this.x / l, this.y / l, this.z / l);
    }
    /**
     * Determines whether any coordinate is undefined.
     *
     * @return {@code true} if any coordinate is {@code NaN}, {@code false} otherwise
     */
    public boolean coordinatesNotDefined() {
        return Double.isNaN(this.x) || Double.isNaN(this.y) || Double.isNaN(this.z);
    }
    /**
     * Determines whether any coordinate is infinite or NaN.
     *
     * @return {@code true} if any coordinate is infinite or NaN, {@code false} otherwise
     */
    public boolean isNotFinite() {
        return !Double.isFinite(this.x) || !Double.isFinite(this.y) || !Double.isFinite(this.z);
    }
    /**
     * Determines whether all coordinates are equal to zero.
     *
     * @return {@code true} if all coordinates equal zero, {@code false} otherwise
     */
    public boolean isZero() {
        return x == 0 && y == 0 && z == 0;
    }
}