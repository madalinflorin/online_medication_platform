package ro.tuc.ds2020.repositories;

import org.aspectj.asm.internal.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.Medications_for_plan;

public interface MedicationsForPlanRepository extends JpaRepository<Medications_for_plan, Relationship> {
}
