package object.strategy.use;

import command.Action2P;
import language.Article;
import lombok.Getter;
import object.interfaces.AdvObject;
import object.interfaces.Lock;
import process.Response;
import util.AdvStringBuilder;
import util.Word;

public enum LockState {
    LOCKED {
        @Getter private final String description = "It's locked";

        @Override
        public Response use(LockUseStrategy lockUseStrategy, String label, AdvObject object, Lock lock) {
            Response response = lock.unlock(object);
            if (response.isSuccess()) {
                lockUseStrategy.setLockState(UNLOCKED);
                response.setOutput("You unlocked the " + label);
            } else if (response.getOutput() == null) {
                response.setOutput(AdvStringBuilder.buildSentence(Word.YOU, Word.CANNOT, Action2P.USE, Article.DEFINITE, object, "on", label));
            }

            return response;
        }
    },
    UNLOCKED {
        @Getter private String description = "It's unlocked";

        @Override
        public Response use(LockUseStrategy lockUseStrategy, String label, AdvObject object, Lock lock) {
            Response response = lock.lock(object);
            if (response.isSuccess()) {
                lockUseStrategy.setLockState(LOCKED);
                response.setOutput("You locked the " + label);
            } else if (response.getOutput() == null) {
                response.setOutput(AdvStringBuilder.buildSentence(Word.YOU, Word.CANNOT, Action2P.USE, Article.DEFINITE, object, "on", label));
            }

            return response;
        }
    };

    public Response use(LockUseStrategy lockUseStrategy, String label, AdvObject object, Lock lock) {return null;}
    public String getDescription() {return null;}
}
