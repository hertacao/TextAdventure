package object.quality_interface;

public interface Lockable extends AdvObject {
    boolean isLocked();
    Key getKey();
}