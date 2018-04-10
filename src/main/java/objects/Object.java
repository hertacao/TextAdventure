package objects; /**
 * Created by Herta on 18.01.2018.
 */
import lombok.*;
import commands.*;
import util.*;
import java.util.EnumMap;
import java.util.LinkedList;

@Getter
@Setter
@ToString(of="name")
@NoArgsConstructor
public abstract class Object {
    protected Integer id;
    protected String name;
    protected String description;
    protected String long_description;
    protected LinkedList<Action> executable;
    protected Response response;
    protected String output;

    protected EnumMap<Action, String> pos_output = new EnumMap<Action, String>(Action.class);
    protected EnumMap<Action, String> no_output = new EnumMap<Action, String>(Action.class);

    public Object(@NonNull String name) {
        this.name = name;
        this.description = "This is a " + this.toString();
        this.executable = new LinkedList<Action>();
        this.response = new Response();

        pos_output.put(Action.LOOK, "You look at the " + this.toString());
        pos_output.put(Action.EXAMINE, "You examine the " + this.toString());
        pos_output.put(Action.PICK_UP, "You pick up a " + this.toString());
        pos_output.put(Action.OPEN, "You open the " + this.toString());
        pos_output.put(Action.CLOSE, "You close the " + this.toString());
        pos_output.put(Action.PUSH, "You push the " + this.toString());
        pos_output.put(Action.PULL, "You pull up the " + this.toString());
        pos_output.put(Action.GO, "You go to the " + this.toString());
        pos_output.put(Action.USE, "You use the " + this.toString());
        pos_output.put(Action.GIVE, "You give the " + this.toString());
        pos_output.put(Action.TALK, "You talk to " + this.toString());
        pos_output.put(Action.COMBINE, "You combine " + this.toString());

        no_output.put(Action.LOOK, "You can't look at the " + this.toString());
        no_output.put(Action.EXAMINE, "You can't examine the " + this.toString());
        no_output.put(Action.PICK_UP, "You can't pick up a " + this.toString());
        no_output.put(Action.OPEN, "You can't open the " + this.toString());
        no_output.put(Action.CLOSE, "You can't close the " + this.toString());
        no_output.put(Action.PUSH, "You can't push the " + this.toString());
        no_output.put(Action.PULL, "You can't pull up the " + this.toString());
        no_output.put(Action.GO, "You can't go to the " + this.toString());
        no_output.put(Action.USE, "You can't use the " + this.toString());
        no_output.put(Action.GIVE, "You can't give the " + this.toString());
        no_output.put(Action.TALK, "You can't talk to " + this.toString());
        no_output.put(Action.COMBINE, "You can't combine " + this.toString());
    }

    public String possible() {
        String output = "You can ";
        for (Action a : this.executable) {
            output += a.toString() + ", ";
        }
        output += "it";
        return output;
    }

    public Response look() {
        if (this.executable.contains(Action.LOOK))  {
            this.response.setSuccess(true);
            this.response.setOutput(this.pos_output.get(Action.LOOK) + this.description);
        }
        else {
            this.response.setSuccess(false);
            this.response.setOutput(this.no_output.get(Action.LOOK));
        }
        return this.response;
    }
    public Response examine() {
        if (this.executable.contains(Action.EXAMINE))  {
            this.response.setSuccess(true);
            if (long_description != null) {
                this.response.setOutput(this.pos_output.get(Action.EXAMINE) + this.long_description);
            } else {
                this.response.setOutput(this.pos_output.get(Action.EXAMINE) + this.description);
            }
        }
        else {
            this.response.setSuccess(false);
            this.response.setOutput(this.no_output.get(Action.EXAMINE));
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
            this.response.setOutput(this.no_output.get(Action.PICK_UP));
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
            this.response.setOutput(this.no_output.get(Action.OPEN));
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
            this.response.setOutput(this.no_output.get(Action.CLOSE));
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
            this.response.setOutput(this.no_output.get(Action.PUSH));
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
            this.response.setOutput(this.no_output.get(Action.PULL));
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
            this.response.setOutput(this.no_output.get(Action.GO));
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
            this.response.setOutput(this.no_output.get(Action.USE));
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
            this.response.setOutput(this.no_output.get(Action.GIVE));
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
            this.response.setOutput(this.no_output.get(Action.TALK));
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
            this.response.setOutput(this.no_output.get(Action.COMBINE));
        }
        return this.response;
    }
}
