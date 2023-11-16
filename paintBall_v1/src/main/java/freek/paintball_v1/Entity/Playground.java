package freek.paintball_v1.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_playground")
public class Playground {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pg_ID;

    private String pg_Name;
    private String pg_Description;
    private int pg_Price;
    private int pg_Area;
    private int pg_Capacity;
}
