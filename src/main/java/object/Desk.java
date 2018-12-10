package object;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Desk extends Composite {
    public Desk(String name, String label, int numb_drawer) {
        super(name, label);
        for (int i = 0; i<numb_drawer; i++) {
            Box drawer = new Box("drawer"+i, "drawer"+i,true, false);
            this.addPart(drawer);
        }
    }

    public Desk(String name, String label, Collection<Box> drawers) {
        super(name, label);
        drawers.forEach(this::addPart);
    }

    public void addContent (Item item, String drawername) {
        for(Item p : this.parts) {
            if(p.name.equals(drawername) & p instanceof Box) {
                ((Box) p).addContent(item);
            }
        }
    }
}
