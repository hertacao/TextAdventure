package object.mediator;

import lombok.AllArgsConstructor;
import object.interfaces.AdvObject;
import process.Response;

@AllArgsConstructor
public class StandardActionMediator implements ActionMediator{
    private AdvObject object;

    // BaseAction
    public Response pickUp() {
        if(this.object.getUseStrategy().isBlockingState()) {
            return new Response(false, this.object.getUseStrategy().getState());
        } else {
            return this.object.getPickUpStrategy().pickUp(object.getLabel());
        }
    }

    public Response open() {
        if(this.object.getUseStrategy().isBlockingState()) {
            return new Response(false, this.object.getUseStrategy().getState());
        } else {
            return this.object.getOpenStrategy().open(object.getLabel());
        }
    }

    public Response close() {
        if (this.object.getUseStrategy().isBlockingState()) {
            return new Response(false, this.object.getUseStrategy().getState());
        } else {
            return this.object.getCloseStrategy().close(object.getLabel());
        }
    }

    public Response push() {
        return this.object.getPushStrategy().push(object.getLabel());
    }

    public Response pull() {
        return this.object.getPullStrategy().pull(object.getLabel());
    }

    public Response talk() {
        return this.object.getTalkStrategy().talk(object.getLabel());
    }


    // Action2P
    public Response use(AdvObject object) {
        return this.object.getUseStrategy().use(object.getLabel(), object);
    }
}
