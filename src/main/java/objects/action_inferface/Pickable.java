package objects.action_inferface;

import objects.quality_interface.Container;
import process.Response;

public interface Pickable {
    Response pickUp();
    Container getContainer();
}
