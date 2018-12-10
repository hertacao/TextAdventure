package object.interfaces;

import command.Action;
import object.strategy.openclose.CloseStrategy;
import object.strategy.openclose.OpenStrategy;
import object.strategy.pickup.PickUpStrategy;
import object.strategy.pushpull.PullStrategy;
import object.strategy.pushpull.PushStrategy;
import object.strategy.talk.TalkStrategy;
import object.strategy.use.UseStrategy;
import process.Response;
import util.Token;

import java.util.Map;
import java.util.Set;

public interface AdvObject extends Token {
    Integer getId();
    String getName();
    String getLabel();
    Set<String> getReference();
    String getDescription();
    String getLong_description();
    Set<Action> getExecutable();
    Response getResponse();

    Map<Action, String> getPos_output();
    Map<Action, String> getNeg_output();

    Response respondPossibleAction();
    Response respondPositive(Action act, String... output);
    Response respondNegative(Action act, String... output);

    PickUpStrategy getPickUpStrategy();
    OpenStrategy getOpenStrategy();
    CloseStrategy getCloseStrategy();
    PushStrategy getPushStrategy();
    PullStrategy getPullStrategy();
    TalkStrategy getTalkStrategy();
    UseStrategy getUseStrategy();

    Response look();
    Response examine();
    Response go();
    Response pickUp();
    Response open();
    Response close();
    Response push();
    Response pull();
    Response talk();

    String print();

    public boolean canExecute(Action action);

    public boolean addExecutable(Action action);



}
