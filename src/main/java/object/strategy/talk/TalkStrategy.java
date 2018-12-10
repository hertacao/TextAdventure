package object.strategy.talk;

import process.Response;

public interface TalkStrategy {
    public Response talk(String label);
}
