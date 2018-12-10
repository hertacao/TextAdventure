package object.strategy.use;

import object.interfaces.AdvObject;
import process.Response;

public interface UseStrategy {
    Response use(String label, AdvObject object);
    boolean isBlockingState();
    String getState();
}
