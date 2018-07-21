package object;

/**
 * Created by Herta on 18.01.2018.
 */

import lombok.*;
import command.*;
import object.action_inferface.Examinable;
import object.action_inferface.Goable;
import object.action_inferface.Lookable;
import object.quality_interface.AdvObject;
import process.Response;
import util.IDType;
import util.Token;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractAdvObject implements AdvObject, Lookable, Examinable, Goable, Token {

    // unique identifier
    protected Integer id;
    // visible unique name for creator
    protected String name;
    // printed name for player
    protected String label;
    protected String description;
    protected String long_description;
    protected LinkedList<Action> executable;
    protected Response response;
    protected IDType definingIDType;

    // Custom responses of this object to an action
    protected Map<Action, String> pos_output = new HashMap<>();
    protected Map<Action, String> neg_output = new HashMap<>();

    public AbstractAdvObject(@NonNull String name) {
        this.name = name;
        this.label = name;
        this.build();
    }

    public AbstractAdvObject(@NonNull String name, @NonNull String label) {
        this.name = name;
        this.label = label;
        this.build();
    }

    protected void build(){
        this.description = "This is a " + this.getLabel() + ". ";
        this.executable = new LinkedList<>();
        this.response = new Response();
        this.executable.add(BaseAction.LOOK);
        this.executable.add(BaseAction.EXAMINE);
        this.executable.add(BaseAction.GO);
        this.definingIDType = IDType.NONE;
    }

    public Response respondPossibleAction() {
        StringBuilder output = new StringBuilder("You can ");
        for (Action a : this.executable) {
            output.append(a.toString());
            output.append(", ");
        }
        output.append("it. ");
        this.response.setOutput(output.toString());
        return this.response;
    }

    /**
     * Sets positive Response for a given action and if a specified string
     * @param act
     * @param output
     * @return
     */
    public Response respondPositive(Action act, String... output) {
        this.response.setSuccess(true);
        if (this.pos_output.get(act) != null) {
            this.response.setOutput(this.pos_output.get(act));
        } else if (output.length != 0) {
            this.response.setOutput(output[0]);
        } else {
            this.response.setOutput(act.pos_output() + this.getLabel() + ". ");
        }

        return this.response;
    }

    /**
     *
     * @param act
     * @param output
     * @return
     */
    public Response respondNegative(Action act, String... output) {
        this.response.setSuccess(false);
        if (this.neg_output.get(act) != null) {
            this.response.setOutput(this.neg_output.get(act));
        } else if (output.length != 0) {
            this.response.setOutput(output[0]);
        } else {
            this.response.setOutput(act.neg_output() + this.getLabel() + ". ");
        }

        return this.response;
    }

    public Response look() {
        if (this.executable.contains(BaseAction.LOOK))  {
            this.respondPositive(BaseAction.LOOK, this.description);
        }
        else {
            this.respondNegative(BaseAction.LOOK);
        }
        return this.response;
    }

    public Response examine() {
        if (this.executable.contains(BaseAction.EXAMINE))  {
            if (long_description != null) {
                this.respondPositive(BaseAction.LOOK, this.long_description);
            } else {
                this.respondPositive(BaseAction.LOOK, this.description);
            }
        }
        else {
            this.respondNegative(BaseAction.EXAMINE);
        }
        return this.response;
    }

    public Response go() {
        return this.act(BaseAction.GO);
    }

    /**
     * If you have a pretty boring behaviour, you can just redirect your function here
     * @param action
     * @return
     */
    protected Response act (Action action) {
        if (this.executable.contains(action))  {
            this.respondPositive(action);
        }
        else {
            this.respondNegative(action);
        }
        return this.response;
    }

    public String toString() {
        return this.label;
    }
}
