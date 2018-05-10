package objects;

import java.util.ArrayList;
import java.util.List;

import commands.*;
import lombok.Getter;
import lombok.Setter;
import util.*;
/**
 * Created by Herta on 18.01.2018.
 */
@Setter
@Getter
public class Scene extends AdvObject implements Container{
    // Connected scenes, like other rooms
    private List<Scene> connected;
    // Contained items, like keys, doors, ..
    private List<Item> content;

    public Scene(String name) {
        super(name);
        this.connected = new ArrayList<>();
        this.content = new ArrayList<>();
        executable.add(Action.GO);
        executable.add(Action.LOOK);
    }

    @Override
    public Response look() {
        if (this.executable.contains(Action.LOOK)) {
            String output = this.description + "\n";

            if (!this.content.isEmpty()) {
                output += "There is a ";
                for (Item i : content) {
                    output += i.getLabel() + ", ";
                }
            }
            this.setPosResponse(Action.LOOK, output);
        } else {
            this.setNegResponse(Action.LOOK);
        }
        return this.response;
    }

    @Override
    public Response examine() {
        if (this.executable.contains(Action.EXAMINE)) {
            String output;

            if (long_description != null) {
                output = this.long_description;
            } else {
                output = this.description;
            }

            if (!this.content.isEmpty()) {
                for (Item i : content) {
                    output += "There is a " + i.getLabel() + ". ";
                    output += i.getDescription() + "\n";
                }
            }
            this.setPosResponse(Action.LOOK, output);
        } else {
            this.look();
        }
        return this.response;
    }
}
