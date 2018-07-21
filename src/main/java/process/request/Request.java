package process.request;

import lombok.*;
import process.Response;
import build.Game;

import java.util.List;

@Data
@RequiredArgsConstructor
public abstract class Request{
    // tokenized input
    @NonNull protected List<String> input;

    public Response invoke(Game game, Object... args) {
        return null;
    }

}
