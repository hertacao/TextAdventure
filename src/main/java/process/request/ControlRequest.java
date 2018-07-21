package process.request;

import commands.BaseControl;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import process.Response;
import build.Game;

import java.util.List;

@Getter
@ToString
public class ControlRequest extends Request {
    @NonNull private BaseControl control;
    private boolean yes;

    public ControlRequest(List<String> input, BaseControl control, boolean yes) {
        super(input);
        this.control = control;
        this.yes = yes;
    }

    @Override
    public Response invoke(Game game, Object... args) {
        return this.control.exec(game, yes);
    }
}
