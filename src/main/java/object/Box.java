package object;

import command.BaseAction;
import lombok.Getter;
import lombok.Setter;
import process.Response;

import java.util.*;

/**
 * Created by Herta on 22.01.2018.
 */
@Getter
@Setter
public class Box extends Container {

    public Box(String name, String label, boolean closed, boolean locked) {
        super(name, label);

        this.reference.add("box");
    }

    private void createContentNameMap() {
        for (Item item: this.contents) {
            for (String name : item.getReference() ) {
                if (this.contentNameMap.containsKey(name)) {
                    this.contentNameMap.get(name).add(item);
                } else {
                    this.contentNameMap.put(name, Arrays.asList(item));
                }
            }
        }
    }

    public boolean isContentAccessible() {
        return !this.closed;
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
