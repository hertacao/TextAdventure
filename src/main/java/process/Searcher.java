package process;

import commands.Action;
import commands.Control;
import lombok.Getter;
import lombok.Setter;
import objects.AdvObject;
import util.*;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Searches the input String for Commands
 */
@Getter
@Setter
public class Searcher {
    private Scanner sc;
    private String input;
    private LinkedList<Control> cont;
    private LinkedList<Action> act;
    private LinkedList<AdvObject> obj;
    private Game game;

    public Searcher() {
        sc = new Scanner(System.in);
        cont = new LinkedList<>();
        act = new LinkedList<>();
        obj = new LinkedList<>();
    }

    public Boolean bool_search() {
        this.read();
        if (input.contains("yes") || input.equals("y")) {
            return true;
        } else if (input.contains("no") || input.equals("n")) {
            return false;
        }
        return null;
    }

    public void search() {
        this.read();
        //Insert Fix line command like Where am I? ...
        this.searchControl();
        this.searchAction();
        this.searchObject();

        //clear up after new line is required
    }

    private void read() {
        String input = sc.nextLine();
    }

    private void searchControl() {
        for (Control c : Control.values()) {
            if (input.contains(c.toString())) {
                this.cont.add(c);
            }
        }
    }

    private void searchAction() {
        for (Action a : Action.values()) {
            if (input.contains(a.toString())) {
                act.add(a);
            }
        }
    }

    private void searchObject() {
        for (AdvObject o : game.getReachable()) {
            if (input.contains(o.getLabel())) {
                obj.add(o);
            }
        }
    }
}
