package process.request;

import lombok.Getter;
import lombok.ToString;
import util.Token;

import java.util.List;

@Getter
@ToString
public class HelpRequest extends Request {
    private List<? extends Token> token;

    public HelpRequest(List<String> input, List<? extends Token> token) {
        super(input);
        this.token = token;
    }
}
