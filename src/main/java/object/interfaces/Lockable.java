package object.interfaces;

/**
 * objects that can be locked for example boxes, doors
 */
public interface Lockable extends AdvObject {
    boolean isLocked();
    Lock getLock();
}
