package objects;

import commands.Action;
import lombok.NonNull;
import util.Response;

import java.util.List;
import java.util.Set;

/**
 * Created by Herta on 10.05.2018.
 */
public abstract class PushItem extends Item{
    protected List<String> positions;
    protected Set<String> reachables;
    protected String currentPosition;
    protected int currentIndex;

    public PushItem(
            @NonNull String name,
            @NonNull String label,
            @NonNull List<String> positions,
            @NonNull Set<String> reachables,
            @NonNull String currentPosition) {
        super(name, label);
        this.positions = positions;
        this.reachables = reachables;
        this.currentPosition = currentPosition;
        this.currentIndex = this.positions.indexOf(currentPosition);
        this.executable.add(Action.PUSH);
        this.executable.add(Action.PULL);
    }

    @Override
    public Response push() {
        if(this.reachables.contains(this.positions.get(this.currentIndex + 1)) && this.executable.contains(Action.PUSH)) {
            this.currentIndex++;
            this.currentPosition = this.positions.get(this.currentIndex);
            this.response.setSuccess(true);
            this.setPosResponse(Action.PUSH, Action.PUSH.pos_output() + this.getLabel() + " to " + currentPosition);
        } else {
            this.setNegResponse(Action.PUSH);
        }
        return this.response;
    }
}
