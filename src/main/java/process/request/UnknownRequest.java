package process.request;

import lombok.ToString;
import process.Response;
import build.Game;

import java.util.List;

@ToString
public class UnknownRequest extends Request {
    public UnknownRequest(List<String> input) {
        super(input);
    }

    @Override
    public Response invoke(Game game, Object... args) {
        return new Response("I don't know what you want to do. Say help to open the help menu.", false);
    }
}
