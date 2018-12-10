package object.strategy.use;

import lombok.NoArgsConstructor;
import lombok.Setter;
import object.interfaces.AdvObject;
import object.interfaces.Lock;
import process.Response;

@NoArgsConstructor
public class LockUseStrategy implements UseStrategy{
    @Setter private LockState lockState = LockState.LOCKED;
    @Setter protected Lock lock;

    public LockUseStrategy(Lock lock) {
        this.lock = lock;
    }

    @Override
    public Response use(String label, AdvObject object) {
       return this.lockState.use(this, label, object, this.lock);
    }

    private boolean isLocked() {
        if (lockState ==  LockState.LOCKED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isBlockingState() {
        return this.isLocked();
    }

    @Override
    public String getState() {
        return this.lockState.getDescription();
    }
}
