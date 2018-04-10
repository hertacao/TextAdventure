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
public class Box extends Openable implements Container{
    private LinkedList<Item> contained;

    public Box(String name, boolean opened, LinkedList<Item> contained) {
        super(name, opened);
        this.contained = contained;
    }

    @Override
    public Response look() {
        this.response.setSuccess(true);
        output = this.pos_output.get(Action.LOOK) + this.description;

        if (!this.contained.isEmpty() && this.opened) {
            output += "There is a ";
            for (Item i: contained) {
                output += i.toString() + ", ";
            }
        }

        this.response.setOutput(output);

        return this.response;
    }

    @Override
    public Response examine() {
        if (this.executable.contains(Action.EXAMINE)) {
            this.response.setSuccess(true);
            if (long_description != null) {
                output = this.pos_output.get(Action.EXAMINE) + this.long_description;
            } else {
                output = this.pos_output.get(Action.EXAMINE) + this.description;
            }

            if (!this.contained.isEmpty() && this.opened) {
                for (Item i : contained) {
                    output += "There is a " + i.toString() + ". ";
                    output += i.getDescription();
                }
            }
            this.response.setOutput(output);
        } else {
            this.look();
        }
        return this.response;
    }
}
