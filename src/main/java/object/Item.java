package object;

import process.Response;
import util.AdvStringBuilder;

/**
 * Created by Herta on 22.01.2018.
 */
public abstract class Item extends AbstractAdvObject {
    public Item(String name) { this(name, name); }
    public Item(String name, String label) { super(name ,label);}

    @Override
    public Response respondPossibleAction() {
        return new Response( AdvStringBuilder.enumerate("You can", this.executable, "this " + this.label), true);
    }
}
