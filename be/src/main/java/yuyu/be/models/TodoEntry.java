package yuyu.be.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer entry_id;
    private String description;
    private Integer status_id;
}
