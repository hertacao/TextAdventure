package process;

import command.*;
import object.interfaces.AdvObject;
import process.request.*;
import util.Token;

import java.util.*;
import java.util.stream.Collectors;

public class Builder {
    /**
     * Tokenize input and searches for key wordMap
     * @return request (e.g. HelpRequest, ActionRequest...)
     */
    public Request buildRequest(Map<String, List<?>> classified) {
        List<String> input = (List<String>) classified.get("input");
        LinkedList<Action> action;
        LinkedList<Control> control;
        LinkedList<Token> other;
        LinkedList<AdvObject> object;

        // Build HelpRequest
        if (this.control.contains(BaseControl.HELP)) {
            List<Token> token = new LinkedList<>();
            token.addAll(this.control);
            token.addAll(this.action);
            token.addAll(this.object);
            token.remove(BaseControl.HELP);
            this.last_com = BaseControl.HELP;
            return new HelpRequest(input, token);
        }

        // Build ControlRequest with EXIT
        if (this.last_com == BaseControl.EXIT && game.isExit()) {
            return new ControlRequest(this.input, BaseControl.EXIT, this.searchBool(this.input));
        }

        // Check for any control commands
        switch (this.control.size()) {
            case 0: break;
            case 1: this.last_com = this.control.getFirst();
                if (this.control.getFirst() instanceof BaseControl) {
                    return new ControlRequest(this.input, (BaseControl) this.control.getFirst(), true);
                } else if (this.control.getFirst() instanceof Status) {
                    return new StatusRequest(this.input, (Status) this.control.getFirst());
                }
            default: return new InvalidMultipleCommandRequest(this.input, this.control);
        }

        // Check for action commands
        if (this.action.size() == 0) {
            if (this.last_com instanceof OnePredicateAction) {
                switch (this.object.size()) {
                    case 0: return new UnknownRequest(this.input);
                    case 1: this.last_obj = object.getFirst();
                        return new OnePredicateRequest(this.input, (OnePredicateAction) this.last_com, object.getFirst());
                    default: Map<String, List<AdvObject>> label = this.checkSameLabel(this.object, 1);
                        List<AdvObject> id_object = label.values().stream()
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList());
                        if (label.isEmpty()) {
                            return new InvalidMultipleObjectRequest(this.input, (Action) this.last_com, this.object);
                        } else if (id_object.isEmpty()) {
                            if (Collections.disjoint(label.keySet(), this.last_obj.getReference())) {
                                return new InvalidUnclearLabelRequest(this.input, (Action) this.last_com, label.keySet());
                            } else {
                                return new OnePredicateRequest(this.input, (OnePredicateAction) this.last_com, this.last_obj);
                            }
                        } else {
                            this.last_obj = id_object.get(0);
                            return new OnePredicateRequest(this.input, (OnePredicateAction) this.last_com, id_object.get(0));
                            // System.out.println("ERROR");
                        }
                }
            } else if (this.last_com instanceof TwoPredicateAction) {
                switch (this.object.size()) {
                    case 0: return new UnknownRequest(this.input);
                    case 1: this.last_obj = this.object.getFirst();
                        return new TwoPredicateRequest(this.input, (TwoPredicateAction) this.last_com, this.object.getFirst(), this.last_obj);
                    case 2: this.last_obj = this.object.getLast();
                        return new TwoPredicateRequest(this.input, (TwoPredicateAction) this.last_com, this.object.getFirst(), this.object.getLast());
                    default: Map<String, List<AdvObject>> label = this.checkSameLabel(this.object, 2);
                        List<AdvObject> id_object = label.values().stream()
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList());
                        if (label.isEmpty()) {
                            return new InvalidMultipleObjectRequest(this.input, (Action) this.last_com, this.object);
                        } else if (id_object.isEmpty()) {
                            return new InvalidUnclearLabelRequest(this.input, (Action) this.last_com, label.keySet());
                        } else {
                            this.last_obj = id_object.get(1);
                            return new TwoPredicateRequest(this.input, (TwoPredicateAction) this.last_com, id_object.get(0), id_object.get(1));
                        }
                }
            } else { return new UnknownRequest(this.input); }

        } else if (this.action.size() == 1) {
            if (this.action.getFirst() instanceof OnePredicateAction) {
                switch (this.object.size()) {
                    case 0: this.last_com = this.action.getFirst();
                        return new OnePredicateRequest(this.input, (OnePredicateAction) this.action.getFirst(), last_obj);
                    case 1: this.last_com = this.action.getFirst();
                        this.last_obj = this.object.getFirst();
                        return new OnePredicateRequest(this.input, (OnePredicateAction) this.action.getFirst(), this.object.getFirst());
                    default: Map<String, List<AdvObject>> label = this.checkSameLabel(this.object, 1);
                        List<AdvObject> id_object = label.values().stream()
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList());
                        if (label.isEmpty()) {
                            return new InvalidMultipleObjectRequest(this.input, this.action.getFirst(), this.object);
                        } else if (id_object.isEmpty()) {
                            if (Collections.disjoint(label.keySet(), this.last_obj.getReference())) {
                                return new InvalidUnclearLabelRequest(this.input, (Action) this.last_com, label.keySet());
                            } else {
                                return new OnePredicateRequest(this.input, (OnePredicateAction) this.action.getFirst(), this.last_obj);
                            }
                        } else {
                            this.last_obj = id_object.get(0);
                            return new OnePredicateRequest(this.input, (OnePredicateAction) this.action.getFirst(), id_object.get(0));
                        }
                }
            } else if (this.action.getFirst() instanceof TwoPredicateAction) {
                switch (this.object.size()) {
                    case 0: this.last_com = this.action.getFirst();
                        return new InvalidNoObjectRequest(this.input, this.action.getFirst());
                    case 1: this.last_com = this.action.getFirst();
                        this.last_obj = this.object.getFirst();
                        return new TwoPredicateRequest(this.input, (TwoPredicateAction) this.action.getFirst(), this.object.getFirst(), last_obj);
                    case 2: this.last_com = this.action.getFirst();
                        this.last_obj = this.object.getLast();
                        return new TwoPredicateRequest(this.input, (TwoPredicateAction) this.action.getFirst(), this.object.getFirst(), this.object.getLast());
                    default: Map<String, List<AdvObject>> label = this.checkSameLabel(this.object, 2);
                        List<AdvObject> id_object = label.values().stream()
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList());
                        if (label.isEmpty()) {
                            return new InvalidMultipleObjectRequest(this.input, this.action.getFirst(), this.object);
                        } else if (id_object.isEmpty()) {
                            return new InvalidUnclearLabelRequest(this.input, this.action.getFirst(), label.keySet());
                        } else {
                            this.last_obj = id_object.get(1);
                            return new TwoPredicateRequest(this.input, (TwoPredicateAction) this.action.getFirst(), id_object.get(0), id_object.get(1));
                        }
                }
            }
        } else { return new InvalidMultipleCommandRequest(this.input, this.action); }
        return null;
    }
}
