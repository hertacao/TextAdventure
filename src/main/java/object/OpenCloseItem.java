package object;

import command.BaseAction;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import object.action_inferface.Closable;
import object.action_inferface.Openable;
import object.quality_interface.Key;
import object.quality_interface.Lockable;
import process.Response;

/**
 * Created by Herta on 22.01.2018.
 */
@Getter
@Setter
public abstract class OpenCloseItem extends Item implements Openable, Closable, Lockable {
    @Getter protected boolean closed;
    @Getter protected boolean locked;
    @Getter protected Key key;

    public OpenCloseItem(@NonNull String name, @NonNull String label, boolean closed, boolean locked) {
        super(name, label);
        this.closed = closed;
        this.locked = locked;
        this.executable.add(BaseAction.OPEN);
        this.executable.add(BaseAction.CLOSE);
    }

    @Override
    public Response open() {
        if(this.executable.contains(BaseAction.OPEN)) {
            if (this.closed) {
                if (!this.locked) {
                    this.closed = false;
                    this.respondPositive(BaseAction.OPEN);
                } else {
                    this.respondNegative(BaseAction.OPEN, "It's locked.");
                }
            } else {
                this.respondNegative(BaseAction.OPEN, "It's already open.");
            }
        } else {
            this.respondNegative(BaseAction.OPEN);
        }
        return this.response;
    }

    @Override
    public Response close() {
        if(this.executable.contains(BaseAction.CLOSE)) {
            if (!this.closed) {
                this.closed = true;
                this.respondPositive(BaseAction.CLOSE);
            } else {
                this.respondNegative(BaseAction.CLOSE, "It's already closed");
            }
        } else {
            this.respondNegative(BaseAction.CLOSE);
        }
        return this.response;
    }
}
