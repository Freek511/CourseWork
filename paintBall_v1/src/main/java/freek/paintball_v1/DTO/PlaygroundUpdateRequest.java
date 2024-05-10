package freek.paintball_v1.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaygroundUpdateRequest {
    private String name;
    private String description;
    private int area;
    private int capacity;
    private int price;
}
