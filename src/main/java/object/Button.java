package object;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Herta on 05.05.2018.
 */
public class Button extends PushItem {
    public Button() {
        super("Button","Button", Arrays.asList("on", "off"), new HashSet<>(Arrays.asList("on", "off")), "off");
        this.reference.add("button");
    }
}
