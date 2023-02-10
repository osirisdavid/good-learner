package tech.nocountry.goodlearnerbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "commission_subject")
public class CommissionSubject implements Serializable {

    @Id
    @Column(name = "id_commission_subject", nullable = false)
    private Long idCommissionSubject;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    //@Column(name = "subject_id")
    private Subject subjectId;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    //@Column(name = "teacher_id")
    private Teacher teacherId;

    @ManyToOne
    @JoinColumn(name = "day_id", nullable = false)
    //@Column(name = "day_id")
    private Day dayId;

    @ManyToOne
    @JoinColumn(name = "commission_id", nullable = false)
    //@Column(name = "commission_id")
    private Commission commissionId;

    @Column(name = "start_time", nullable = false)
    private Time startTime;

    @Column(name = "end_time", nullable = false)
    private Time endTime;
}
