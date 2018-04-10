package objects;

import commands.Action;
import lombok.Getter;
import lombok.Setter;
import util.*;
/**
 * Created by Herta on 22.01.2018.
 */
@Getter
@Setter
public abstract class Openable extends Item {
    protected boolean opened;

    public Openable(String name) {
        super(name);
        this.executable.add(Action.CLOSE);
        this.executable.add(Action.OPEN);
        this.opened = false;
    }

    public Openable(String name, boolean opened) {
        super(name);
        this.executable.add(Action.CLOSE);
        this.executable.add(Action.OPEN);
        this.opened = opened;
    }

    @Override
    public Response open() {
        if(!this.opened) {
            this.opened = true;
            this.response.setSuccess(true);
            this.response.setOutput(this.pos_output.get(Action.OPEN));
        } else {
            this.response.setSuccess(false);
            this.response.setOutput("It's already open");
        }
        return this.response;
    }

    @Override
    public Response close() {
        if(this.opened) {
            this.opened = false;
            this.response.setSuccess(true);
            this.response.setOutput(this.pos_output.get(Action.CLOSE));
        } else {
            this.response.setSuccess(false);
            this.response.setOutput("It's already closed");
        }
        return this.response;
    }
}
