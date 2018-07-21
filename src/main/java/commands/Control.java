package commands;

import process.Response;
import build.Game;

public interface Control extends Command{
    Response exec(Game game, boolean yes);
}
