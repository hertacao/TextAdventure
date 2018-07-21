package process;

import build.Game;
import command.*;
import lombok.Getter;
import lombok.Setter;
import object.quality_interface.AdvObject;
import process.request.*;
import util.*;
import language.Language;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Searches the input String for Commands
 */
@Getter
@Setter
public class Parser {
    private Language language;
    private Scanner sc;
    private Game game;
    private Executer executer;
    private AdvObject last_obj;
    private Command last_com;
    private List<String> input;
    private LinkedList<Action> action;
    private LinkedList<Control> control;
    private LinkedList<AdvObject> object;

    public Parser(Language language, Executer executer, Game game) {
        this.language = language;
        this.executer = executer;
        this.game = game;
        sc = new Scanner(System.in);
        this.last_obj = game.getLocation();
        this.input = new LinkedList<>();
        this.action = new LinkedList<>();
        this.control = new LinkedList<>();
        this.object = new LinkedList<>();
    }

    /**
     * Tokenize input and searches for key words
     * @return request (e.g. HelpRequest, ActionRequest...)
     */
    public Request buildRequest() {
        this.tokenize();
        this.classify(this.input);

        // Build HelpRequest
        if (this.control.contains(BaseControl.HELP)) {
            List<Token> token = new LinkedList<>();
            token.addAll(this.control);
            token.addAll(this.action);
            token.remove(BaseControl.HELP);
            this.last_com = BaseControl.HELP;
            return new HelpRequest(input, token);
        }

        // Build ControlRequest with EXIT
        if (this.last_com == BaseControl.EXIT && game.isExit()) {
            return new ControlRequest(this.input, BaseControl.EXIT, this.searchBool(this.input));
        }

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
                            return new InvalidUnclearLabelRequest(this.input, (Action) this.last_com, label.keySet());
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
                            // System.out.println("ERROR");
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
                            return new InvalidUnclearLabelRequest(this.input, this.action.getFirst(), label.keySet());
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

    private void tokenize() {
        this.input.clear();
        String line = sc.nextLine();
        Scanner tokenizer = new Scanner(line);
        while(tokenizer.hasNext()) {
            this.input.add(tokenizer.next());
        }
        tokenizer.close();
    }
    
    private void classify(List<String> input) {
        this.action.clear();
        this.control.clear();
        input.stream().map(w -> this.language.findToken(w))
        .forEach(
                t -> {
                    if (t instanceof Action) {
                        this.action.add((Action) t);
                    } else if (t instanceof Control) {
                        this.control.add((Control) t);
                    }
        });
        this.object = this.searchObject(input);
    }

    private LinkedList<AdvObject> searchObject(List<String> input) {
        return game.getReachable().stream().filter(o -> input.contains(o.getLabel())).collect(Collectors.toCollection(LinkedList::new));
    }

    private LinkedList<Direction> searchDirection(List<String> input) {
        LinkedList<Direction> direction = new LinkedList<>();
        Arrays.stream(CardinalDirection.values()).filter(a -> input.contains(a.toString())).forEach(direction::add);
        Arrays.stream(RelativeDirection.values()).filter(a -> input.contains(a.toString())).forEach(direction::add);
        return direction;
    }

    private LinkedList<? extends Token> searchOther(List<String> input, IDType idType) {
        switch(idType) {
            case DIRECTION: {
                LinkedList<Direction> direction = new LinkedList<>();
                Arrays.stream(CardinalDirection.values()).filter(a -> input.contains(a.toString())).forEach(direction::add);
                Arrays.stream(RelativeDirection.values()).filter(a -> input.contains(a.toString())).forEach(direction::add);
                return direction;
            }
            default: return null;
        }
    }

    private Boolean searchBool(List<String> input) {
        return input.contains("yes");
    }

    private Map<String, List<AdvObject>> checkSameLabel(List<AdvObject> object, int predicate) {
        if (predicate > 2 || predicate < 1) {
            // raise Exception;
        }

        Map<String, List<AdvObject>> label = object.stream()
                .collect(Collectors.groupingBy(AdvObject::getLabel));

        int max_ObjectPerLabel;
        if (label.size() == 1) { max_ObjectPerLabel = predicate;
        } else if(predicate == 2 && label.size() == 2) { max_ObjectPerLabel = 1;
        } else { return Collections.emptyMap(); }

        for (Map.Entry<String, List<AdvObject>> entry : label.entrySet()) {
            Set<IDType> idType = entry.getValue().stream()
                    .collect(Collectors.groupingBy(AdvObject::getDefiningIDType))
                    .keySet();
            //idType.forEach(System.out::println);
            // special handling if the object are defined by direction, need to look for directions in Scene
            if (idType.size() == 1 && idType.contains(IDType.DIRECTION)) {
                //System.out.println("Special");
                List<Direction> input_direction = this.searchDirection(input);
                //input_direction.forEach(System.out::println);
                List<AdvObject> connector = game.getLocation().getDirection().entrySet().stream()
                        .filter(d -> input_direction.contains(d.getValue()))
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
                //connector.forEach(System.out::println);
                //System.out.println("connector size:" + connector.size());
                if (connector.size() <= max_ObjectPerLabel) {
                    label.put(entry.getKey(), connector);
                } else {
                    label.put(entry.getKey(), Collections.emptyList());
                }
                // define object by their description, please complete
            } else {
                List<? extends Token> identifier = idType.stream()
                        .map(i -> this.searchOther(this.input, i))
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());
                //return null;
            }
        }
        return label;
    }
    
    public String toString() {
        StringBuilder output = new StringBuilder("input: ");
        output.append(this.input);
        output.append('\n');
        output.append("control: ");
        output.append(this.control);
        output.append("    ");
        output.append("action: ");
        output.append(this.action);
        output.append("    ");
        output.append("object: ");
        this.object.stream().map(AdvObject::getName).forEach(o -> {output.append(o); output.append(", ");});
        output.append('\n');
        output.append("last_obj: ");
        output.append(this.last_obj.getName());
        output.append("    ");
        output.append("last_com: ");
        output.append(this.last_com);
        
        return output.toString();
    }
}
