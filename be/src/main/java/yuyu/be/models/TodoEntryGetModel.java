package yuyu.be.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="entry_list")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoEntryGetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer entry_id;
    private String description;
    private String status_name;
}
