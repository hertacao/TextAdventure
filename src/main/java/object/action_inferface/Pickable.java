package object.action_inferface;

import object.quality_interface.Container;
import process.Response;

public interface Pickable {
    Response pickUp();
    Container getContainer();
}
