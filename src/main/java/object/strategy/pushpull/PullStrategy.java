package object.strategy.pushpull;

import process.Response;

public interface PullStrategy {
    Response pull(String label);
}
