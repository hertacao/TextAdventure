package object.action_inferface;

import process.Response;

public interface Closable {
    Response close();
    boolean isClosed();
}
