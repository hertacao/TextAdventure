package process; /**
 * Created by Herta on 18.01.2018.
 */
import commands.Action;
import commands.Command;
import commands.Control;
import lombok.Getter;
import lombok.Setter;
import objects.Object;
import util.Response;

import java.util.*;

import static commands.Control.HELP;

/**
 * Builds commands
 *
 * @author Herta Cao
 */
@Getter
@Setter
public class Handler {
    private Executer executer;
    private Searcher searcher;

    private Object last_obj;
    private Command last_com;
    private Response response;

    public Handler() {
    }

    public void handle() {
        LinkedList<Control> cont = this.searcher.getCont();
        LinkedList<Action> act = this.searcher.getAct();
        LinkedList<Object> obj = this.searcher.getObj();

        switch (cont.size()) {
            case 0: this.action(act, obj);
            case 1: executer.execControl(cont.getFirst());
            default: {
                if (cont.contains(HELP)) {
                    executer.execControl(HELP);
                } else {
                    this.specifyCommand(cont, act);
                }
            }
        }
    }

    private void action(LinkedList<Action> act, LinkedList<Object> obj) {
        switch (act.size()) {
            case 0: this.zeroAction(act, obj);
            case 1: this.oneAction(act, obj);
            default: this.specifyCommand(null, act);
        }
    }

    private void oneAction(LinkedList<Action> act, LinkedList<Object> obj) {
        // Action Command
        switch (obj.size()) {
            // Zero Objects
            case 0: {
                obj.add(last_obj);
                this.response = executer.execAction(act.getFirst(), obj);
                last_com = act.getFirst();
            }
            // One Object
            case 1: {
                this.response = executer.execAction(act.getFirst(), obj);
                last_obj = obj.getLast();
                last_com = act.getFirst();
            }

            // Two Object
            case 2: {
                this.response = executer.execAction(act.getFirst(), obj);
                last_obj = obj.getLast();
                last_com = act.getFirst();
            }
            // Multiple Objects
            default: {
                this.response = executer.specifyObject(obj);
            }
        }
    }

    // Contextual Interpretation
    private void zeroAction(LinkedList<Action> act, LinkedList<Object> obj) {
        switch (obj.size()) {
            // Zero Objects
            case 0: {
                this.response.setOutput("I don't know what you want to do. Say help to open the help menu.");
                this.response.setSuccess(false);
            }
            // One Object
            case 1: {
                if (last_com instanceof Control) {
                    this.response.setOutput("Please say what you want to do with " + obj.getFirst().toString());
                    this.response.setSuccess(false);
                }
                else if (last_com instanceof Action) {
                    this.response = executer.execAction((Action) last_com, obj);
                    last_obj = obj.getLast();
                }
            }
            // Multiple Objects
            default: {
                this.response = executer.specifyObject(obj);
            }
        }
    }

    private void specifyCommand(LinkedList<Control> cont, LinkedList<Action> act) {
        String output = "You entered more than one command which are ";
        for (Control c : cont) {
            output += c.toString();
        }
        for (Action a : act) {
            output += a.toString();
        }
        output += "Please specify which command you like to use";
        this.response.setOutput(output);
        this.response.setSuccess(false);
    }

    private void help() {

    }

    private void print(Response response) {
        System.out.print(response.getOutput());
    }

}