package by.morunov.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Alex Morunov
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
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
