package object.strategy.pushpull;

import process.Response;

public interface PushStrategy {
    Response push(String label);
}
