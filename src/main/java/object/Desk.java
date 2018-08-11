package object;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Desk extends Composite {
    public Desk(String name, String label, int numb_drawer) {
        super(name, label);
        for (int i = 0; i<numb_drawer; i++) {
            Box drawer = new Box("drawer"+i, "drawer"+i, new HashSet<>(), true, false);
            this.addPart(drawer);
        }
    }

    public Desk(String name, String label, Collection<Box> drawers) {
        super(name, label);
        drawers.forEach(this::addPart);
    }
}
