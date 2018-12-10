package object;

/**
 * Created by Herta on 18.01.2018.
 */

import command.Action;
import command.BaseAction;
import language.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import object.interfaces.AdvObject;
import object.mediator.ActionMediator;
import object.mediator.StandardActionMediator;
import object.strategy.openclose.CloseStrategy;
import object.strategy.openclose.NoCloseStrategy;
import object.strategy.openclose.NoOpenStrategy;
import object.strategy.openclose.OpenStrategy;
import object.strategy.pickup.NoPickUpStrategy;
import object.strategy.pickup.PickUpStrategy;
import object.strategy.pushpull.NoPullStrategy;
import object.strategy.pushpull.NoPushStrategy;
import object.strategy.pushpull.PullStrategy;
import object.strategy.pushpull.PushStrategy;
import object.strategy.talk.NoTalkStrategy;
import object.strategy.talk.TalkStrategy;
import object.strategy.use.NoUseStrategy;
import object.strategy.use.UseStrategy;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractAdvObject implements AdvObject {

    // unique identifier
    protected Integer id;
    // visible unique name for creator
    protected String name;
    // printed name for player
    protected String label;
    // List of alternative String referring to this object
    protected Set<String> reference = new HashSet<>();
    // short and long description of this object
    protected String description;
    protected String long_description;

    // list of Actions that can be performed on this object
    protected Set<Action> executable = new HashSet<>();
    // Response created by object
    protected Response response = new Response();
    // Container where this object is currently stored
    @Setter protected Container home;
    // Custom responses of this object to an action, tbd??
    protected Map<Action, String> pos_output = new HashMap<>();
    protected Map<Action, String> neg_output = new HashMap<>();

    protected ActionMediator actionMediator = new StandardActionMediator(this);

    // behaviour for every action is excluded in a strategy pattern
    // for BaseAction
    @Setter @Getter protected PickUpStrategy pickUpStrategy = NoPickUpStrategy.getInstance();
    @Setter @Getter protected OpenStrategy openStrategy = NoOpenStrategy.getInstance();
    @Setter @Getter protected CloseStrategy closeStrategy = NoCloseStrategy.getInstance();
    @Setter @Getter protected PushStrategy pushStrategy = NoPushStrategy.getInstance();
    @Setter @Getter protected PullStrategy pullStrategy = NoPullStrategy.getInstance();
    @Setter @Getter protected TalkStrategy talkStrategy = NoTalkStrategy.getInstance();

    // for Action2P
    @Setter @Getter protected UseStrategy useStrategy = NoUseStrategy.getInstance();

    // for Action2PString

    public AbstractAdvObject(@NonNull String name, @NonNull String label) {
        this.name = name;
        this.label = label;
        this.description = "This is a " + this.label + ". ";
        this.executable.add(BaseAction.LOOK);
        this.executable.add(BaseAction.EXAMINE);
        this.executable.add(BaseAction.GO);
        this.reference.add(label);
    }

    public Response respondPossibleAction() {
        return new Response( true, AdvStringBuilder.buildSentence(Word.YOU, Word.CAN, this.executable, Article.DEFINITE, this));
    }

    public boolean canExecute(Action action) {
        return this.executable.contains(action);
    }

    public boolean addExecutable(Action action) {return this.executable.add(action); }

    /**
     * Sets positive Response for a given action and if a specified string
     * @param act
     * @param output
     * @return
     */
    public Response respondPositive(Action act, String... output) {
        this.response.setSuccess(true);
        if (this.pos_output.get(act) != null) {
            this.response.setOutput(this.pos_output.get(act));
        } else if (output.length != 0) {
            this.response.setOutput(output[0]);
        } else {
            this.response.setOutput(AdvStringBuilder.buildSentence(Word.YOU, act, Article.DEFINITE, this.getLabel()));
        }

        return this.response;
    }

    /**
     *
     * @param act
     * @param output
     * @return
     */
    public Response respondNegative(Action act, String... output) {
        this.response.setSuccess(false);
        if (this.neg_output.get(act) != null) {
            this.response.setOutput(this.neg_output.get(act));
        } else if (output.length != 0) {
            this.response.setOutput(output[0]);
        } else {
            this.response.setOutput(AdvStringBuilder.buildSentence(Word.YOU, Word.CANNOT, act, Article.DEFINITE, this.getLabel()));
        }

        return this.response;
    }

    public Response look() {
        if (this.executable.contains(BaseAction.LOOK))  {
            this.respondPositive(BaseAction.LOOK, this.description);
        }
        else {
            this.respondNegative(BaseAction.LOOK);
        }
        return this.response;
    }

    public Response examine() {
        if (this.executable.contains(BaseAction.EXAMINE))  {
            if (long_description != null) {
                this.respondPositive(BaseAction.LOOK, this.long_description);
            } else {
                this.respondPositive(BaseAction.LOOK, this.description);
            }
        }
        else {
            this.respondNegative(BaseAction.EXAMINE);
        }
        return this.response;
    }

    /**
     * Behaviour of each object to a certain action is delegated to their respective strategy
     * Please change that. Not every Object should be GoStrategy
     * @return
     */
    public Response go() {
        return this.respondPositive(BaseAction.GO);
    }

    // BaseAction
    public Response pickUp() {
        return this.actionMediator.pickUp();
    }

    public Response open() {
        return this.actionMediator.open();
    }

    public Response close() {
        return this.actionMediator.close();
    }

    public Response push() {
        return this.actionMediator.push();
    }

    public Response pull() {
        return this.actionMediator.pull();
    }

    public Response talk() {
        return this.actionMediator.talk();
    }


    // Action2P
    public Response use(AdvObject object) {
        return this.actionMediator.use(object);
    }

    public String toString() {
        return this.label;
    }

    public String print() {
        StringBuilder output = new StringBuilder("name: ");
        output.append(this.name);
        output.append("    ");
        output.append("label: ");
        output.append(this.label);
        output.append("    ");
        output.append("ref: ");
        output.append(this.reference);
        output.append('\n');
        output.append("exec: ");
        output.append(this.executable);

        return output.toString();
    }
}
