package object.quality_interface;

import command.Action;
import process.Response;
import util.IDType;
import util.Token;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface AdvObject extends Token {
    Integer getId();
    String getName();
    String getLabel();
    List<String> getReference();
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
