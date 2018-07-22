package object;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import command.BaseAction;
import lombok.Getter;
import lombok.Setter;
import object.quality_interface.Connector;
import object.quality_interface.Container;
import process.Response;
import util.*;

/**
 * Created by Herta on 18.01.2018.
 */
@Setter
@Getter
public class Scene extends AbstractAdvObject implements Container {
    // Connected scenes, like other rooms, part of other rooms or desk
    // Connected connectors like doors or path
    private Map<Connector, Direction> direction;
    private Map<Connector, Scene> links;
    // Contained items, like keys, ..
    private Set<Item> contents;

    public Scene(String name) {
        this(name, name);
    }

    public Scene(String name, String label) {
        super(name, label);
        this.direction = new HashMap<>();
        this.links = new HashMap<>();
        this.contents = new HashSet<>();
        executable.add(BaseAction.GO);
        executable.add(BaseAction.LOOK);
        this.definingIDType = IDType.DIRECTION;
        this.description = "You are in the " + this.getLabel() + ". ";
    }

    public void addConnector(Connector connector, Direction direction, Scene scene) {
        this.direction.put(connector, direction);
        this.links.put(connector, scene);
    }

    public void addContent (Item item) {
        this.contents.add(item);
    }

    @Override
    public Response respondPossibleAction() {
        return new Response( AdvStringBuilder.enumerate("You can", this.executable, "the " + this.label), true);
    }

    @Override
    public Response look() {
        if (this.executable.contains(BaseAction.LOOK)) {
            String output = this.description + '\n';

            if (!this.direction.isEmpty()) {
                Map<Connector, String> detail = new HashMap<>();
                direction.forEach((c, d) -> {
                            if (d instanceof CardinalDirection) {
                                detail.put(c, "facing " + d.toString());
                            } else if (d.equals(RelativeDirection.FRONT)) {
                                detail.put(c, "at the " + d.toString());
                            } else if (d.equals(RelativeDirection.BACK)) {
                                detail.put(c, "in the " + d.toString());
                            } else {
                                detail.put(c, "on the " + d.toString());
                            }
                        }
                );
                output += AdvStringBuilder.enumerate("There is", direction.keySet(), detail, null);
            }

            if (!this.contents.isEmpty()) {
                output += AdvStringBuilder.enumerate("The room contains", contents);
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

            if (!this.contents.isEmpty()) {
                Map<Item, String> detail = contents.stream()
                        .collect(Collectors.toMap(Function.identity(), Item::getDescription));
                output += AdvStringBuilder.enumerate("There is", contents, detail, null);
            }
            this.respondPositive(BaseAction.EXAMINE, output);
        } else {
            this.look();
        }
        return this.response;
    }
}
