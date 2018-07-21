package process;

import lombok.*;

/**
 * Created by Herta on 22.01.2018.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String output;
    private boolean success;

    public void print() {
        System.out.println(this.output);
    }
}
