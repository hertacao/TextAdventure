package objects;

/**
 * Created by Herta on 18.01.2018.
 */

import lombok.*;
import commands.*;
import util.*;
import java.util.EnumMap;
import java.util.LinkedList;

@Getter
@Setter
//@ToString(of = "id, name, label")
@NoArgsConstructor
public abstract class AdvObject {
    
    protected Integer id;
    protected String name;
    protected String label;
    protected String description;
    protected String long_description;
    protected LinkedList<Action> executable;
    protected Response response;

    protected EnumMap<Action, String> pos_output = new EnumMap<>(Action.class);
    protected EnumMap<Action, String> neg_output = new EnumMap<>(Action.class);

    public AdvObject(@NonNull String name) {
        this.name = name;
        this.label = name;
        this.build();
    }

    public AdvObject(@NonNull String name, @NonNull String label) {
        this.name = name;
        this.label = label;
        this.build();
    }

    private void build(){
        this.description = "This is a " + this.getLabel();
        this.executable = new LinkedList<>();
        this.response = new Response();
        this.executable.add(Action.LOOK);
    }

    public String possible() {
        String output = "You can ";
        for (Action a : this.executable) {
            output += a.toString() + ", ";
        }
        output += "it";
        return output;
    }
    protected Response setPosResponse(Action act) {
        this.response.setSuccess(true);
        if (this.pos_output.get(act) != null) {
            this.response.setOutput(this.pos_output.get(act));
        } else {
            this.response.setOutput(act.pos_output() + this.getLabel());
        }
        
        return this.response;
    }

    protected Response setPosResponse(Action act, String output) {
        this.response.setSuccess(true);
        if (this.pos_output.get(act) != null) {
            this.response.setOutput(this.pos_output.get(act));
        } else {
            this.response.setOutput(output);
        }

        return this.response;
    }

    protected Response setNegResponse(Action act) {
        this.response.setSuccess(false);
        if (this.pos_output.get(act) != null) {
            this.response.setOutput(this.neg_output.get(act));
        } else {
            this.response.setOutput(act.neg_output() + this.getLabel());
        }

        return this.response;
    }

    protected Response setNegResponse(Action act, String output) {
        this.response.setSuccess(false);
        if (this.pos_output.get(act) != null) {
            this.response.setOutput(this.neg_output.get(act));
        } else {
            this.response.setOutput(output);
        }

        return this.response;
    }

    private Response action (Action act) {
        if (this.executable.contains(act))  {
            this.setPosResponse(act);
        }
        else {
            this.setNegResponse(act);
        }
        return this.response;
    }

    public Response look() {
        if (this.executable.contains(Action.LOOK))  {
            this.setPosResponse(Action.LOOK, this.description);
        }
        else {
            this.setNegResponse(Action.LOOK);
        }
        return this.response;
    }
    public Response examine() {
        if (this.executable.contains(Action.EXAMINE))  {
            if (long_description != null) {
                this.setPosResponse(Action.LOOK, this.long_description);
            } else {
                this.setPosResponse(Action.LOOK, this.description);
            }
        }
        else {
            this.setNegResponse(Action.EXAMINE);
        }
        return this.response;
    }
    public Response pick_up() {
        if (this.executable.contains(Action.PICK_UP))  {
            this.response.setSuccess(true);
            this.response.setOutput(this.pos_output.get(Action.PICK_UP));
        }
        else {
            this.response.setSuccess(false);
            this.response.setOutput(this.neg_output.get(Action.PICK_UP));
        }
        return this.response;
    }
    public Response open() {
        if (this.executable.contains(Action.OPEN))  {
            this.response.setSuccess(true);
            this.response.setOutput(this.pos_output.get(Action.OPEN));
        }
        else {
            this.response.setSuccess(false);
            this.response.setOutput(this.neg_output.get(Action.OPEN));
        }
        return this.response;
    }
    public Response close() {
        if (this.executable.contains(Action.CLOSE))  {
            this.response.setSuccess(true);
            this.response.setOutput(this.pos_output.get(Action.CLOSE));
        }
        else {
            this.response.setSuccess(false);
            this.response.setOutput(this.neg_output.get(Action.CLOSE));
        }
        return this.response;
    }
    public Response push() {
        if (this.executable.contains(Action.PUSH))  {
            this.response.setSuccess(true);
            this.response.setOutput(this.pos_output.get(Action.PUSH));
        }
        else {
            this.response.setSuccess(false);
            this.response.setOutput(this.neg_output.get(Action.PUSH));
        }
        return this.response;
    }
    public Response pull() {
        if (this.executable.contains(Action.PULL))  {
            this.response.setSuccess(true);
            this.response.setOutput(this.pos_output.get(Action.PULL));
        }
        else {
            this.response.setSuccess(false);
            this.response.setOutput(this.neg_output.get(Action.PULL));
        }
        return this.response;
    }
    public Response go_to() {
        if (this.executable.contains(Action.GO))  {
            this.response.setSuccess(true);
            this.response.setOutput(this.pos_output.get(Action.GO));
        }
        else {
            this.response.setSuccess(false);
            this.response.setOutput(this.neg_output.get(Action.GO));
        }
        return this.response;
    }
    public Response use() {
        if (this.executable.contains(Action.USE))  {
            this.response.setSuccess(true);
            this.response.setOutput(this.pos_output.get(Action.USE));
        }
        else {
            this.response.setSuccess(false);
            this.response.setOutput(this.neg_output.get(Action.USE));
        }
        return this.response;
    }
    public Response give() {
        if (this.executable.contains(Action.GIVE))  {
            this.response.setSuccess(true);
            this.response.setOutput(this.pos_output.get(Action.GIVE));
        }
        else {
            this.response.setSuccess(false);
            this.response.setOutput(this.neg_output.get(Action.GIVE));
        }
        return this.response;
    }
    public Response talk() {
        if (this.executable.contains(Action.TALK))  {
            this.response.setSuccess(true);
            this.response.setOutput(this.pos_output.get(Action.TALK));
        }
        else {
            this.response.setSuccess(false);
            this.response.setOutput(this.neg_output.get(Action.TALK));
        }
        return this.response;
    }
    public Response combine() {
        if (this.executable.contains(Action.COMBINE))  {
            this.response.setSuccess(true);
            this.response.setOutput(this.pos_output.get(Action.COMBINE));
        }
        else {
            this.response.setSuccess(false);
            this.response.setOutput(this.neg_output.get(Action.COMBINE));
        }
        return this.response;
    }
}
