package object;

import command.BaseAction;
import lombok.Getter;
import lombok.Setter;
import object.quality_interface.Container;
import object.quality_interface.Lockable;
import process.Response;

import java.util.Set;

/**
 * Created by Herta on 22.01.2018.
 */
@Getter
@Setter
public class Box extends OpenCloseItem implements Container, Lockable {
    private Set<Item> contents;

    public Box(String name, String label, Set<Item> contents, boolean closed, boolean locked) {
        super(name, label, closed, locked);
        this.contents = contents;
        this.reference.add("box");
    }

    public void addContent (Item item) {
        this.contents.add(item);
    }

    @Override
    public Response look() {
        if (this.executable.contains(BaseAction.LOOK)) {
            String output = this.description + "\n";

            if (!this.closed) {
                if (this.contents.isEmpty()) {
                    output += "It's empty. ";
                } else {
                    output += "There is a ";
                    for (Item i : contents) {
                        output += i.getLabel() + ", ";
                    }
                    output += "inside. ";
                }
            }
            this.respondPositive(BaseAction.LOOK, output);
        } else {
            this.respondNegative(BaseAction.LOOK);
        }
        return this.response;
    }

    @Override
    public Response examine() {
        if (this.executable.contains(BaseAction.EXAMINE)) {
            String output;

            if (long_description != null) {
                output = this.long_description;
            } else {
                output = this.description;
            }

            if (!this.closed) {
                if (this.contents.isEmpty()) {
                    output += "It's empty. ";
                } else {
                    for (Item i : contents) {
                        output += "There is a " + i.getLabel() + ". " + i.getDescription();
                    }
                }
            }
            this.respondPositive(BaseAction.EXAMINE, output);
        } else {
            this.look();
        }
        return this.response;
    }
}
