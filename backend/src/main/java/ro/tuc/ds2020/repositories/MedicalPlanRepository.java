package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.Medical_plan;

import java.util.UUID;

public interface MedicalPlanRepository extends JpaRepository<Medical_plan, UUID> {


}