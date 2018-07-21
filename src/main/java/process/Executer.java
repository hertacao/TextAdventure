package process;

import process.request.Request;
import build.Game;

/**
 * Created by Herta on 19.01.2018.
 */
public class Executer {

    // process.Executer for Controls
    public Response invokeRequest(Request request, Game game) {
        System.out.println(request.toString());
        return request.invoke(game);
    }

    /*
    private void help() {

        this.response.setOutput("This is the help menu");
        this.response.setSuccess(true);
    }

    private void inventory() {
        String output = "You have ";
        for (Item i : game.getInventory()) {
            output += "a" + i.getLabel();
        }
    }

    private void exit(boolean bool) {
        int i = 0;
        do {
            this.response.setOutput("Are your sure you want to exit?");
            this.response.setSuccess(true);
            if (bool) {
                System.exit(0);
            } else if (!bool) {
                continue;
            } else {
                i++;
            }
        } while (i < 3);
    }*/
}
