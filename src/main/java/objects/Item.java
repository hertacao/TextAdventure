package objects;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Created by Herta on 22.01.2018.
 */
//@NoArgsConstructor
//@RequiredArgsConstructor
public abstract class Item extends AbstractAdvObject {
    public Item(String name) { super(name);}
    public Item(String name, String label) { super(name ,label);}
}
