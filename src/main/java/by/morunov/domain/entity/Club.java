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
@Table(name = "clubs")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nameTeam")
    private String nameTeam;



}
