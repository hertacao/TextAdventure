package object.strategy.openclose;

import command.BaseAction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import object.strategy.use.LockState;
import process.Response;

@NoArgsConstructor
public class BaseOpenCloseStrategy implements OpenStrategy, CloseStrategy {
    @Setter private OpenClosedState openClosedState = OpenClosedState.CLOSED;

    @Override
    public Response open(String label) {
        return this.openClosedState.open(this, label);
    }

    @Override
    public Response close(String label) {
        return this.openClosedState.close(this, label);
    }

    public boolean isClosed() {
        if (this.openClosedState == OpenClosedState.CLOSED) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOpen() {
        if (this.openClosedState == OpenClosedState.OPEN) {
            return true;
        } else {
            return false;
        }
    }
}
