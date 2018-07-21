package objects;

import commands.BaseAction;
import lombok.NonNull;
import objects.action_inferface.Pullable;
import process.Response;

import java.util.List;
import java.util.Set;

/**
 * Created by Herta on 05.05.2018.
 */
public abstract class PushPullItem extends PushItem implements Pullable {

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
        if(this.reachables.contains(this.positions.get(this.currentIndex - 1)) && this.executable.contains(BaseAction.PULL)) {
            this.currentIndex--;
            this.currentPosition = this.positions.get(this.currentIndex);
            this.respondPositive(BaseAction.PULL, BaseAction.PULL.pos_output() + this.getLabel() + " to " + currentPosition);
        } else {
            this.respondNegative(BaseAction.PULL);
        }
        return this.response;
    }
}
