package object.strategy.openclose;

import process.Response;

public interface CloseStrategy {
    Response close(String label);
    boolean isClosed();
}
