package object.locks;

import object.Item;
import object.interfaces.Lock;
import process.Response;

public class CombinationLock extends Item implements Lock {
    private String combination;

    public CombinationLock(String name) {
        super(name);
    }

    public Response unlock(Object entry) {
        if (entry instanceof String) {
            if (this.combination == entry) {
                this.response.setSuccess(true);
            } else {
                this.response.setSuccess(false);
                this.response.setOutput("Nothing happens.");
            }
        } else {
            this.response.setSuccess(false);
        }
        return this.response;
    }

    public Response lock(Object entry) {
        return null;
    }
}
