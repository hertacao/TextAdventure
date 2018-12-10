package object.strategy.openclose;

import process.Response;

public interface OpenStrategy {
    Response open(String label);
    boolean isOpen();
}
