package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.Caregiver_patient;
import ro.tuc.ds2020.entities.RelationshipPK2;


public interface CaregiverPatientRepository extends JpaRepository<Caregiver_patient, RelationshipPK2> {
}
