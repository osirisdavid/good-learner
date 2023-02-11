package tech.nocountry.goodlearnerbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.nocountry.goodlearnerbackend.model.CommissionSubject;

@Repository
public interface CommissionSubjectRepository extends JpaRepository<CommissionSubject, Long> {
}
