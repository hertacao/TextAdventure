package process.request;

import build.Game;
import command.Action;
import lombok.ToString;
import process.Response;

import java.util.List;

@ToString
public class InvalidNoObjectRequest extends Request {
    private Action action;

    public InvalidNoObjectRequest(List<String> input, Action action) {
        super(input);
        this.action = action;
    }

    @Override
    public Response invoke (Game game, Object... args) {
        return new Response("Please tell which two object you like to " + this.action.toString() + ".", false);
    }
}
