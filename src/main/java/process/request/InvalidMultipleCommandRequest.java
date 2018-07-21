package process.request;

import command.Command;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import process.Response;
import util.AdvStringBuilder;
import build.Game;

import java.util.List;

@Getter
@ToString
public class InvalidMultipleCommandRequest extends Request {
    @NonNull private List<? extends Command> command;

    public InvalidMultipleCommandRequest(List<String> input, List<? extends Command> command) {
        super(input);
        this.command = command;
    }

    @Override
    public Response invoke(Game game, Object... args) {
        return new Response( AdvStringBuilder.enumerateCommand("You entered more than one command which are ", this.command, "Please specify which command you like to use"), false);
    }
}
