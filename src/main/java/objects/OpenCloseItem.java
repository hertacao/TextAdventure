package objects;

import commands.Action;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import util.*;
/**
 * Created by Herta on 22.01.2018.
 */
@Getter
@Setter
public abstract class OpenCloseItem extends Item{
    protected boolean opened;

    public OpenCloseItem(@NonNull String name) {
        super(name);
        this.build();
        this.opened = false;
    }

    public OpenCloseItem(@NonNull String name, @NonNull String label, boolean opened) {
        super(name, label);
        this.build();
        this.opened = opened;
    }

    private void build() {
        this.executable.add(Action.CLOSE);
        this.executable.add(Action.OPEN);
    }

    @Override
    public Response open() {
        if(this.executable.contains(Action.OPEN)) {
            if (!this.opened) {
                this.opened = true;
                this.setPosResponse(Action.OPEN);
            } else {
                this.setNegResponse(Action.OPEN, "It's already open");
            }
        } else {
            this.setNegResponse(Action.OPEN);
        }
        return this.response;
    }

    @Override
    public Response close() {
        if(this.executable.contains(Action.CLOSE)) {
            if (this.opened) {
                this.opened = false;
                this.setPosResponse(Action.CLOSE);
            } else {
                this.setNegResponse(Action.CLOSE, "It's already closed");
            }
        } else {
            this.setNegResponse(Action.CLOSE);
        }
        return this.response;
    }
}
