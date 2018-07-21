package build;

import lombok.Getter;
import lombok.Setter;
import objects.quality_interface.AdvObject;
import objects.Item;
import objects.Scene;
import process.Response;
import util.AdvStringBuilder;

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
    private Set<AdvObject> discovered;
    private Set<AdvObject> reachable;
    private Scene location;
    private Response response;
    private boolean exit;

    public Game(Scene start) {
        this.inventory = new HashSet<>();
        this.discovered = new HashSet<>();
        this.reachable = new HashSet<>();
        this.location = start;
        this.response = new Response();
        this.exit = false;
        discovered.add(start);
        reachable.add(start);
    }

    public Response respondLocation() {
        this.response.setOutput("You are in the " + this.location.getLabel());
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
        output.append("reachable: ");
        this.reachable.stream().map(AdvObject::getName).forEach(i -> {
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
