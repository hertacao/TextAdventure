package objects;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Created by Herta on 22.01.2018.
 */
@NoArgsConstructor
@RequiredArgsConstructor
public abstract class Item extends Object{
    public Item(String name) {
        super(name);
    }
}
