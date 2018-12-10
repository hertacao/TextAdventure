package object.mediator;

import object.interfaces.AdvObject;
import process.Response;

public interface ActionMediator {
    Response pickUp();
    Response open();
    Response close();
    Response push();
    Response pull();
    Response talk();
    Response use(AdvObject object);
}
