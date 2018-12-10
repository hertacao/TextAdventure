package object.locks;

import lombok.Setter;
import object.Item;
import object.interfaces.Lock;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

import java.util.ArrayList;
import java.util.List;

public class MultiLock extends Item implements Lock {
    private List<Key> keys;
    private List<Key> locked;
    @Setter private boolean respectOrder = false;

    public MultiLock(String name, String... keynames) {
        super(name);
        this.keys = new ArrayList<Key>();
        for (String kname: keynames) {
            Key key = new Key(kname);
            this.keys.add(key);
            key.setLock(this);
        }
        this.locked = keys;
    }

    @Override
    public Response unlock(Object entry) {
        if (entry instanceof Key) {
            if ((respectOrder & this.locked.get(0) == entry) || (!respectOrder & this.locked.contains(entry)) ) {
                locked.remove(entry);
                if(locked.isEmpty()) {
                    this.response.setSuccess(true);
                } else {
                    this.response.setSuccess(false);
                    this.response.setOutput("You hear a click.");
                }
            } else {
                this.response.setSuccess(false);
                this.response.setOutput("Nothing happens.");
            }
        } else {
            this.response.setSuccess(false);
        }
        return this.response;
    }

    @Override
    public Response lock(Object entry) {
        this.response.setSuccess(false);
        this.response.setOutput(AdvStringBuilder.buildSentence(Word.YOU, Word.CANNOT, "lock", "this"));
        return this.response;
    }
}
