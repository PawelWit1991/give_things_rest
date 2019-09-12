package pl.pw.give_things_rest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Institution {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String location;
    private String target;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Donation> donation;
}
