package process.request;

import build.Game;
import command.Action;
import lombok.NonNull;
import lombok.ToString;
import object.quality_interface.AdvObject;
import process.Response;
import util.AdvStringBuilder;

import java.util.List;

@ToString
public class InvalidMultipleObjectRequest extends Request{
    @NonNull private Action action;
    @NonNull private List<AdvObject> object;

    public InvalidMultipleObjectRequest(List<String> input, Action action, List<AdvObject> object) {
        super(input);
        this.action = action;
        this.object = object;
    }

    @Override
    public Response invoke (Game game, Object... args) {
        return new Response(AdvStringBuilder.enumerate("You entered more than one object which are", object,
                "Please specify which object you like to " + this.action.toString() + "."), false);
    }
}
