package object;

import command.BaseAction;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import object.interfaces.Lock;
import object.strategy.openclose.BaseOpenCloseStrategy;
import object.strategy.use.LockUseStrategy;

/**
 * Created by Herta on 22.01.2018.
 */
@Getter
@Setter
public abstract class OpenCloseItem extends Item {

    public OpenCloseItem(@NonNull String name, @NonNull String label) {
        super(name, label);
        this.closeStrategy = new BaseOpenCloseStrategy();
        this.openStrategy = new BaseOpenCloseStrategy();
        this.executable.add(BaseAction.OPEN);
        this.executable.add(BaseAction.CLOSE);
    }

    public boolean isClosed() {
        return this.closeStrategy.isClosed();
    }

    public boolean isOpen() {
        return this.openStrategy.isOpen();
    }

    public void addLock(Lock lock) {
        this.useStrategy = new LockUseStrategy(lock);
    }
}
