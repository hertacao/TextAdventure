package object.quality_interface;

import object.Item;

import java.util.Set;

/**
 * A Container is defined as something containing other Items
 * Created by Herta on 22.01.2018.
 */
public interface Container extends AdvObject {
    Set<Item> getContents();
    void addContent(Item item);

}
