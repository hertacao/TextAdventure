package object.strategy.pickup;

import process.Response;

public interface PickUpStrategy {
    Response pickUp(String label);
}
