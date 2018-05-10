package process;

import commands.Action;
import commands.Control;
import objects.*;
import objects.AdvObject;
import util.*;

import java.util.LinkedList;


/**
 * Created by Herta on 19.01.2018.
 */
public class Executer {
    private Game game;
    private Response response;
    private Searcher searcher;

    public Executer(Searcher searcher) {
        this.searcher = searcher;
    }

    // process.Executer for Controls
    public Response execControl(Control cont) {
        switch (cont) {
            case HELP: this.help();
            case INVENTORY: this.inventory();
            case EXIT: this.exit();
            //case HINT: this.hint();
        }
        return this.response;
    }

    private void help() {

        this.response.setOutput("This is the help menu");
        this.response.setSuccess(true);
    }

    private void inventory() {
        String output = "You have ";
        for (Item i : game.getInventory()) {
            output += "a" + i.getLabel();
        }
    }

    private void exit() {
        int i = 0;
        do {
            this.response.setOutput("Are your sure you want to exit?");
            this.response.setSuccess(true);
            Boolean bool = searcher.bool_search();
            if (bool) {
                System.exit(0);
            } else if (!bool) {
                continue;
            } else {
                i++;
            }
        } while (i < 3);
    }

    public Response execAction(Action act, LinkedList<AdvObject> obj) {
        switch(act) {
            case LOOK: this.look(obj);
            case EXAMINE: this.examine(obj);
            case PICK_UP: this.pick_up(obj);
            case OPEN: this.open(obj);
            case CLOSE: this.close(obj);
            case PUSH: this.push(obj);
            case PULL: this.pull(obj);
        }
        return this.response;
    }

    private void look(LinkedList<AdvObject> obj) {
        AdvObject o = obj.getFirst();
        if (obj.size() == 1) {
            if (o.look().isSuccess() && o instanceof Container) {
                game.getDiscovered().addAll(((Container) o).getContent());
                game.getReachable().addAll(((Container) o).getContent());
            }
            this.response = o.look();
        } else {
            this.specifyObject(obj);
        }
    }

    private void examine(LinkedList<AdvObject> obj) {
        AdvObject o = obj.getFirst();
        if (obj.size() == 1) {
            if (o.examine().isSuccess() && o instanceof Container) {
                game.getDiscovered().addAll(((Container) o).getContent());
                game.getReachable().addAll(((Container) o).getContent());
            }
            this.response = o.examine();
        } else {
            this.specifyObject(obj);
        }
    }

    private void pick_up(LinkedList<AdvObject> obj) {
        AdvObject o = obj.getFirst();
        if (obj.size() == 1) {
            if (o.pick_up().isSuccess()) {
                game.getInventory().add((CustomItem) o);
                game.getLocation().getContent().remove(o);
            }
            this.response = o.pick_up();
        } else {
            this.specifyObject(obj);
        }
    }

    private void open(LinkedList<AdvObject> obj) {
        AdvObject o = obj.getFirst();
        if (obj.size() == 1) {
            if (o.open().isSuccess() && o instanceof Container) {
                game.getReachable().addAll(((Container) o).getContent());
            }
            this.response = o.open();
        } else {
            this.specifyObject(obj);
        }
    }

    private void close(LinkedList<AdvObject> obj) {
        AdvObject o = obj.getFirst();
        if (obj.size() == 1) {
            if (o.close().isSuccess() && o instanceof Container) {
                game.getReachable().removeAll(((Container) o).getContent());
            }
            this.response = o.close();
        } else {
            this.specifyObject(obj);
        }
    }

    private void push(LinkedList<AdvObject> obj) {
        AdvObject o = obj.getFirst();
        if (obj.size() == 1) {
            this.response = o.push();
        } else {
            this.specifyObject(obj);
        }
    }

    private void pull(LinkedList<AdvObject> obj) {
        AdvObject o = obj.getFirst();
        if (obj.size() == 1) {
            this.response = o.pull();
        } else {
            this.specifyObject(obj);
        }
    }

    public Response specifyObject(LinkedList<AdvObject> obj) {
        String output = "You entered more than one object which are ";
        for (AdvObject o : obj) {
            output += o.getLabel();
        }
        output += "Please specify which object you like to use";
        this.response.setOutput(output);
        this.response.setSuccess(false);
        return this.response;
    }
}
