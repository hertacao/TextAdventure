package object;

import java.util.ArrayList;
import java.util.List;

public abstract class Composite extends Item{
    protected List<Item> parts;
    public Composite(String name, String label) {
        super(name, label);
        this.parts = new ArrayList<>();
    }

    public void addPart(Item item) {
        this.parts.add(item);
    }
}
