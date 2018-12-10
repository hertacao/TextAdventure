package object;

import command.BaseAction;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import object.strategy.pickup.BasePickUpStrategy;

public class PickUpItem extends Item {
    @Getter @Setter protected Container container;

    public PickUpItem(@NonNull String name, @NonNull String label) {
        super(name, label);
        this.pickUpStrategy = new BasePickUpStrategy();
        this.executable.add(BaseAction.PICK_UP);
    }
}
