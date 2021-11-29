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
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @Column(name = "row")
    private int row;

    @Column(name = "seat")
    private int seat;

    @Column(name = "price")
    private int price;

    public Ticket(Match match, int row, int seat, int price) {
        this.match = match;
        this.row = row;
        this.seat = seat;
        this.price = price;
    }
}
