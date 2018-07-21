package object.quality_interface;

import command.Action;
import process.Response;
import util.IDType;

import java.util.LinkedList;
import java.util.Map;

public interface AdvObject {
    Integer getId();
    String getLabel();
    String getName();
    String getDescription();
    String getLong_description();
    LinkedList<Action> getExecutable();
    Response getResponse();
    IDType getDefiningIDType();

    Map<Action, String> getPos_output();
    Map<Action, String> getNeg_output();

    Response respondPossibleAction();
    Response respondPositive(Action act, String... output);
    Response respondNegative(Action act, String... output);

    Response look();
    Response examine();
    Response go();

}
