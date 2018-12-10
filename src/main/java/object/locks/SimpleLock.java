package object.locks;

import lombok.Getter;
import object.Item;
import object.interfaces.Lock;
import process.Response;

@Getter
public class SimpleLock extends Item implements Lock {
    private Key key;

    public SimpleLock(String name, String keyname) {
        super(name);
        this.key = new Key(keyname);
        this.key.setLock(this);
    }

    @Override
    public Response unlock(Object entry) {
        if (entry instanceof Key) {
            if(this.key == entry) {
                this.response.setSuccess(true);
            } else {
                this.response.setSuccess(false);
                this.response.setOutput("Nothing happens.");
            }
        } else {this.response.setSuccess(false);}
        return this.response;
    }

    @Override
    public Response lock(Object entry) {
        return this.unlock(entry);
    }
}
