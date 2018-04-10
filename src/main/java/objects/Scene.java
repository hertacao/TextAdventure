package objects;

import java.util.LinkedList;
import commands.*;
import lombok.Getter;
import lombok.Setter;
import util.*;
/**
 * Created by Herta on 18.01.2018.
 */
@Setter
@Getter
public class Scene extends Object implements Container{
    // Connected scenes, like other rooms
    private LinkedList<Scene> connected;
    // Contained items, like keys, doors, ..
    private LinkedList<Item> contained;

    public Scene(String name) {
        super(name);
        this.connected = new LinkedList<Scene>();
        this.contained = new LinkedList<Item>();
        executable.add(Action.GO);
        executable.add(Action.LOOK);
    }

    @Override
    public Response look() {
        this.response.setSuccess(true);
        output = this.pos_output.get(Action.LOOK) + this.description;

        if (!this.contained.isEmpty()) {
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

            if (!this.contained.isEmpty()) {
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
