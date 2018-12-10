package object.interfaces;

import process.Response;

public interface Lock {
    public Response lock(Object entry);
    public Response unlock(Object entry);
}
