package process;

import lombok.*;

/**
 * Created by Herta on 22.01.2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private boolean success;
    private String output;

    public String toString() {
        return this.output;
    }
}
