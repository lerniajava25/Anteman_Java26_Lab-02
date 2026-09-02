package raytracer.ray;

public record Ray(Vector3D origin, Vector3D direction) {

    public Ray {
        if (origin == null) {
            throw new IllegalArgumentException("Origin cannot be null");
        }
        if (origin.isNotFinite()) {
            throw new IllegalArgumentException("Origin must be finite");
        }
        if (origin.coordinatesNotDefined()) {
            throw new IllegalArgumentException("Origin coordinates must be defined");
        }
        if (direction == null) {
        throw new IllegalArgumentException("Direction cannot be null");
        }
        if (direction.isNotFinite()) {
            throw new IllegalArgumentException("Direction must be finite");
        }
        if (direction.coordinatesNotDefined()) {
            throw new IllegalArgumentException("Direction coordinates must be defined");
        }
        if (direction.isZero()) {
            throw new IllegalArgumentException("Direction cannot be zero");}
        }
}
