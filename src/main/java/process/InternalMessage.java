package process;

import command.Action;
import command.Command;
import command.Control;
import lombok.Data;
import object.interfaces.AdvObject;
import util.Token;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
public class InternalMessage {
    private AdvObject last_obj;
    private Command last_com;
    private List<String> input = new ArrayList<>();
    private LinkedList<Action> action = new LinkedList<>();
    private LinkedList<Control> control = new LinkedList<>();
    private LinkedList<Token> other = new LinkedList<>();
    private LinkedList<AdvObject> object = new LinkedList<>();

    public void clear() {
        this.input.clear();
        this.action.clear();
        this.control.clear();
        this.other.clear();
        this.object.clear();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("input: ");
        output.append(this.input);
        output.append('\n');
        output.append("control: ");
        output.append(this.control);
        output.append("    ");
        output.append("action: ");
        output.append(this.action);
        output.append("    ");
        output.append("other: ");
        output.append(this.other);
        output.append('\n');
        output.append("object: ");
        this.object.stream().map(AdvObject::getName).forEach(o -> {output.append(o); output.append(", ");});
        output.append('\n');
        output.append("last_obj: ");
        output.append(this.last_obj.getName());
        output.append("    ");
        output.append("last_com: ");
        output.append(this.last_com);

        return output.toString();
    }
}
