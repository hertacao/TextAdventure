package object.strategy.pushpull;

import command.BaseAction;
import language.Article;
import lombok.NonNull;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

import java.util.List;
import java.util.Set;

public class BasePushStrategy implements PushStrategy{
    private SwitchState switchState;
    protected List<SwitchState> positions;

    public BasePushStrategy(String... positions) {
        for (String pos: positions) {
            SwitchState switchState = new SwitchState(pos);
            this.positions.add(switchState);
        }
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
