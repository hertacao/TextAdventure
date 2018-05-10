package objects;

import commands.Action;
import lombok.Getter;
import lombok.Setter;
import util.*;

import java.util.LinkedList;

/**
 * Created by Herta on 22.01.2018.
 */
@Getter
@Setter
public class Box extends OpenCloseItem implements Container{
    private LinkedList<Item> content;

    public Box(String name, String label, boolean opened, LinkedList<Item> content) {
        super(name, label, opened);
        this.content = content;
    }

    @Override
    public Response look() {
        if (this.executable.contains(Action.LOOK)) {
            String output = this.description + "\n";

            if (this.opened) {
                if (this.content.isEmpty()) {
                    output += "It's empty. ";
                } else {
                    output += "There is a ";
                    for (Item i : content) {
                        output += i.getLabel() + ", ";
                    }
                    output += "inside. ";
                }
            }
            this.setPosResponse(Action.LOOK, output);
        } else {
            setNegResponse(Action.LOOK);
        }
        return this.response;
    }

    @Override
    public Response examine() {
        if (this.executable.contains(Action.EXAMINE)) {
            String output;

            if (long_description != null) {
                output = this.pos_output.get(Action.EXAMINE) + this.long_description;
            } else {
                output = this.pos_output.get(Action.EXAMINE) + this.description;
            }

            if (this.opened) {
                if (this.content.isEmpty()) {
                    output += "It's empty. ";
                } else {
                    for (Item i : content) {
                        output += "There is a " + i.getLabel() + ". " + i.getDescription();
                    }
                }
            }
            this.setPosResponse(Action.EXAMINE, output);
        } else {
            this.look();
        }
        return this.response;
    }
}
