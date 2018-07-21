package object;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Herta on 05.05.2018.
 */
public class Button extends PushItem {
    public Button() {
        super("Button","Button", new ArrayList<>(), new HashSet<>(), "off");
        this.positions.add("off");
        this.positions.add("on");
        this.reachables.add("off");
        this.reachables.add("off");
    }
}
