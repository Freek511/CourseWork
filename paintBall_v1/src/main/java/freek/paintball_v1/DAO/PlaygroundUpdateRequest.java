package freek.paintball_v1.DAO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaygroundUpdateRequest {
    private int id;
    private String description;
    private int area;
    private int capacity;
    private int price;
}
