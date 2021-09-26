package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ro.tuc.ds2020.entities.Medication;
import java.util.UUID;

public interface MedicationRepository extends JpaRepository<Medication, UUID> {

    @Transactional
    @Modifying
    @Query(value = "update Medication set id = :newId where id = :id")

    void updateMedication(@Param("id") UUID id,@Param("newId") UUID newId);

    @Transactional
    @Modifying
    @Query(value = "update Medication_effect set id_medication = :newId where id_medication = :id")

    void updateMedicationEffect(@Param("id") UUID id,@Param("newId") UUID newId);
}