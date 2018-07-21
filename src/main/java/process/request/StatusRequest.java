package process.request;

import command.Status;
import lombok.NonNull;
import lombok.ToString;
import process.Response;
import build.Game;

import java.util.List;

@ToString
public class StatusRequest extends Request {
    @NonNull private Status status;

    public StatusRequest(List<String> input, Status status) {
        super(input);
        this.status = status;
    }

    @Override
    public Response invoke(Game game, Object... args) {
        return this.status.exec(game, true);
    }
}
