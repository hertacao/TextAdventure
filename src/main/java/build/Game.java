package build;

import lombok.Getter;
import lombok.Setter;
import object.interfaces.AdvObject;
import object.Item;
import object.Scene;
import object.Container;
import process.Response;
import util.AdvStringBuilder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Herta on 18.01.2018.
 */
@Getter
@Setter
public class Game {
    private boolean running;
    private Set<Item> inventory;
    private Set<AdvObject> discovered; //delete
    private Set<Container> reachableContainer;
    private Scene location;
    private Response response;
    private boolean exit;

    public Game(Scene start) {
        this.inventory = new HashSet<>();
        this.discovered = new HashSet<>();
        this.reachableContainer = new HashSet<>();
        this.reachableContainer.add(start);
        // this.reachable = new HashSet<>();
        this.location = start;
        this.response = new Response();
        this.exit = false;
        discovered.add(start);
    }

    public Response respondLocation() {
        this.response.setOutput("You are in the " + this.location.getLabel() + ". ");
        this.response.setSuccess(true);
        return this.response;
    }

    public Response respondInventory() {
        String output;
        if (this.inventory.isEmpty()) {
            output = "You have nothing in your inventory.";
        } else {
            output = AdvStringBuilder.enumerate("You have", this.inventory);
        }
        this.response.setOutput(output);
        this.response.setSuccess(true);
        return this.response;
    }

    public Response respondExit(boolean yes) {
        //System.out.println("yes: " + yes + "   exit: " + this.exit);
        if (yes) {
            if (this.exit) {
                this.running = false;
                this.response.setOutput("Goodbye!");
                this.response.setSuccess(true);
            } else {
                this.exit = true;
                this.response.setOutput("Are your sure you want to exit?");
                this.response.setSuccess(true);
            }
        } else {
            this.exit = false;
            this.response.setOutput("Ok, let's continue.");
            this.response.setSuccess(false);
        }
        return this.response;
    }

    public void addDiscovered(Collection<AdvObject> discovery) {
        this.discovered.addAll(discovery);
    }

    public void addDiscovered(AdvObject discovery) {
        this.discovered.add(discovery);
    }

    public void addReachableContainer(Container container) {
        this.reachableContainer.add(container);
    }

    public String toString() {
        StringBuilder output = new StringBuilder("location: ");
        output.append(this.location);
        output.append("    ");
        output.append("inventory: ");
        this.inventory.stream().map(AdvObject::getName).forEach(i -> {
                    output.append(i);
                    output.append(", ");
                }
        );
        output.append(System.lineSeparator());
        output.append("reachablContainer: ");
        this.reachableContainer.stream().map(AdvObject::getName).forEach(i -> {
                    output.append(i);
                    output.append(", ");
                }
        );
        output.append("    ");
        output.append("discovered: ");
        this.discovered.stream().map(AdvObject::getName).forEach(i -> {
                    output.append(i);
                    output.append(", ");
                }
        );
        return output.toString();
    }
}
