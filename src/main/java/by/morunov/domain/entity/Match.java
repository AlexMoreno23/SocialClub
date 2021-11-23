package by.morunov.domain.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author Alex Morunov
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "first_club_id")
    private Club team1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "second_club_id")
    private Club team2;

    @Column(name = "dateOfMatch")
    private String dateOfMatch;



}
