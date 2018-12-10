package object.strategy.pickup;

import lombok.Setter;
import process.Response;

public class BasePickUpStrategy implements PickUpStrategy {
    @Setter private Placement placement = Placement.AROUND;

    @Override
    public Response pickUp(String label) {
        return this.placement.pickUp(this, label);
    }
}
