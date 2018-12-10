package object.locks;

import lombok.Getter;
import lombok.Setter;
import object.PickUpItem;
import object.Container;
import object.interfaces.Lock;

public class Key extends PickUpItem {
    @Getter @Setter private Lock lock;

    public Key(String name) {
        super(name, name);
    }

    @Override
    public Container getContainer() {return this.container;}
}
