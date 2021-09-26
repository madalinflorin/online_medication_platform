package ro.tuc.ds2020.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Medication_effect;
import ro.tuc.ds2020.entities.RelationshipPK1;

import java.util.List;

@Repository

public interface MedicationEffectRepository extends JpaRepository<Medication_effect, RelationshipPK1> {

    @Query(value = "select * from medication_effect where id_medication = :id_medication",nativeQuery = true)
    List<Medication_effect> findAllById_medication(@Param("id_medication") byte[] id_medication);

}