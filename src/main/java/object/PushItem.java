package object;

import command.BaseAction;
import lombok.NonNull;
import object.strategy.pushpull.BasePushStrategy;

import java.util.List;
import java.util.Set;

/**
 * Created by Herta on 10.05.2018.
 */
public abstract class PushItem extends Item {
    public PushItem(
            @NonNull String name,
            @NonNull String label,
            @NonNull List<String> positions,
            @NonNull Set<String> reachables,
            @NonNull String currentPosition) {
        super(name, label);
        this.pushStrategy = new BasePushStrategy(positions, reachables, currentPosition);
        this.executable.add(BaseAction.PUSH);
    }
}
