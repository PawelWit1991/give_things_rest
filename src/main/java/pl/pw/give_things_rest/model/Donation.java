package pl.pw.give_things_rest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numberOfBags;

    @OneToOne()
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name = "institution_id")
    private Long institution;

    @Column(name = "user_id")
    private Long user;

    private String address;

    private String zip;

    private String city;

    private String phone;

    private String pickupDate;

    private String pickupTime;

    private String info;
}
