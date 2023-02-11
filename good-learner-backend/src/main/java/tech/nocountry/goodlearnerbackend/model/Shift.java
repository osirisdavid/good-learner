package tech.nocountry.goodlearnerbackend.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "shift")
public class Shift implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shift", nullable = false, unique = true)
    private Long id;

    @Column(name = "shift_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private ShiftName shiftName;
}
