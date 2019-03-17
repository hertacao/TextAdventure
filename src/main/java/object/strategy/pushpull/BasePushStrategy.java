package object.strategy.pushpull;

import command.BaseAction;
import language.Article;
import lombok.NonNull;
import lombok.Setter;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class BasePushStrategy implements PushStrategy{
    @Setter private SwitchState switchState;
    protected List<SwitchState> positions;

    public BasePushStrategy(String... positions) {
        List<String> pos = Arrays.asList(positions);
        this.switchState = new SwitchState(this, pos.iterator().next());
        SwitchState previousState = this.switchState;

        while(pos.iterator().hasNext()) {
            SwitchState nextState = new SwitchState(this, pos.iterator().next());
            previousState.setNext(nextState);
            this.positions.add(previousState);
            previousState = nextState;
        }

        previousState.setNext(this.switchState);
        this.positions.add(previousState);
    }

    @Override
    public Response push(String label) {
        return this.switchState.push(label);
        /*Response response;
        if(this.reachables.contains(this.positions.get(this.currentIndex + 1))) {
            this.currentIndex++;
            this.currentPosition = this.positions.get(this.currentIndex);
            response = new Response(true, AdvStringBuilder.buildSentence(Word.YOU, BaseAction.PUSH, Article.DEFINITE, label, " to ", currentPosition));
        } else {
            response = new Response(true, AdvStringBuilder.buildSentence(Word.YOU, Word.CANNOT, BaseAction.PUSH, Article.DEFINITE, label));
        }
        return response;*/
    }
}
