package object;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import command.BaseAction;
import lombok.Getter;
import lombok.Setter;
import object.interfaces.Connector;
import process.Response;
import util.*;

/**
 * Created by Herta on 18.01.2018.
 */
@Setter
@Getter
public class Scene extends Container {
    // Connected scenes, like other rooms, part of other rooms or desk
    // Connected connectors like doors or path
    protected Map<Connector, Direction> directionMap;
    protected Map<Connector, Scene> sceneMap;

    public Scene(String name) {
        this(name, name);
    }

    public Scene(String name, String label) {
        super(name, label);
        this.directionMap = new HashMap<>();
        this.sceneMap = new HashMap<>();
        executable.add(BaseAction.GO);
        executable.add(BaseAction.LOOK);
        this.description = "You are in the " + this.getLabel() + ". ";
    }

    public void addConnector (Connector connector, Direction direction, Scene scene) {
        this.directionMap.put(connector, direction);
        this.sceneMap.put(connector, scene);
    }

    private void createContentNameMap() {
        for (Item i: this.contents) {
            for (String name : i.getReference()) {
                if (this.contentNameMap.containsKey(name)) {
                    this.contentNameMap.get(name).add(i);
                } else {
                    this.contentNameMap.put(name, Arrays.asList(i));
                }
            }
        }
    }

    public boolean isContentAccessible() {
        return true;
    }


    public List<Scene> getReachableScene() {
        List<Scene> reachableScene = new ArrayList<>();
        for(Map.Entry<Connector,Scene> link : this.sceneMap.entrySet()) {
            if(link.getKey().isPassable()) {
                reachableScene.add(link.getValue());
            }
        }
        return reachableScene;
    }

    @Override
    public Response look() {
        if (this.executable.contains(BaseAction.LOOK)) {
            String output = this.description + '\n';

            if (!this.directionMap.isEmpty()) {
                Map<Connector, String> detail = new HashMap<>();
                directionMap.forEach((c, d) -> {
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
                output += AdvStringBuilder.enumerate("There is", directionMap.keySet(), detail, null);
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
