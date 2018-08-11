package object;

import object.quality_interface.AdvObject;

import java.util.ArrayList;
import java.util.List;

public abstract class Composite extends Item{
    private List<AdvObject> parts;
    public Composite(String name, String label) {
        super(name, label);
        this.parts = new ArrayList<>();
    }

    public void addPart(AdvObject object) {
        this.parts.add(object);
    }
}
