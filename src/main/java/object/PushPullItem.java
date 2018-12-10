package object;

import command.BaseAction;
import lombok.NonNull;
import object.strategy.pushpull.BasePushPullStrategy;

import java.util.List;
import java.util.Set;

/**
 * Created by Herta on 05.05.2018.
 */
public abstract class PushPullItem extends Item {

    public PushPullItem(
            @NonNull String name,
            @NonNull String label,
            @NonNull List<String> positions,
            @NonNull Set<String> reachables,
            @NonNull String currentPosition) {
        super(name, label);
        this.pushStrategy = new BasePushPullStrategy(positions, reachables, currentPosition);
        this.pullStrategy = new BasePushPullStrategy(positions, reachables, currentPosition);
        this.executable.add(BaseAction.PUSH);
        this.executable.add(BaseAction.PULL);
    }
}
