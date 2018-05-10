package objects;

import com.sun.org.apache.bcel.internal.generic.PUSH;
import commands.Action;
import lombok.NonNull;
import util.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Herta on 05.05.2018.
 */
public abstract class PushPullItem extends PushItem{

    public PushPullItem(
            @NonNull String name,
            @NonNull String label,
            @NonNull List<String> positions,
            @NonNull Set<String> reachables,
            @NonNull String currentPosition) {
        super(name, label, positions, reachables, currentPosition);
    }

    @Override
    public Response pull() {
        if(this.reachables.contains(this.positions.get(this.currentIndex - 1)) && this.executable.contains(Action.PULL)) {
            this.currentIndex--;
            this.currentPosition = this.positions.get(this.currentIndex);
            this.setPosResponse(Action.PULL, Action.PULL.pos_output() + this.getLabel() + " to " + currentPosition);
        } else {
            this.setNegResponse(Action.PULL);
        }
        return this.response;
    }
}
