/*package object.strategy;

import command.Action2P;
import command.BaseAction;
import lombok.Getter;
import object.interfaces.Lock;
import object.strategy.openclose.CloseStrategy;
import object.strategy.openclose.OpenStrategy;
import process.Response;

@Getter
public class LockOpenCloseStrategy implements OpenStrategy, CloseStrategy {
    protected boolean closed;
    protected boolean locked;
    protected Lock lock;

    public LockOpenCloseStrategy(boolean closed, boolean locked) {
        this.closed = closed;
        this.locked = locked;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    @Override
    public Response open(String label) {
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
        return this.response;
    }

    @Override
    public Response close(String label) {
        if (!this.closed) {
            this.closed = true;
            this.respondPositive(BaseAction.CLOSE);
        } else {
            this.respondNegative(BaseAction.CLOSE, "It's already closed");
        }
        return this.response;
    }

    @Override
    public Response use(String label) {
        if(this.executable.contains(Action2P.USE)) {
            this.response = this.lock.unlock(object);
            if (this.response.isSuccess()) {
                this.locked = false;
                this.response.setOutput("You unlocked the " + this.label);
            } else if (this.response.getOutput() == null) {
                this.respondNegative(Action2P.USE);
            }
        } else {
            this.respondNegative(Action2P.USE);
        }
        return this.response;
    }
}
*/