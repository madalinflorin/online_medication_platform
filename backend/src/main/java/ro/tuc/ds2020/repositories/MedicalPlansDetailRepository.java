package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.MedicalPlansDetail;

import java.util.List;
import java.util.UUID;

public interface MedicalPlansDetailRepository extends JpaRepository<MedicalPlansDetail, UUID> {


    @Query(value = "SELECT new ro.tuc.ds2020.entities.MedicalPlansDetail(mp.id, m.name, m.dosage, mfp.start_period_tratament, mfp.end_period_tratament, mfp.intake_interval_start, mfp.intake_interval_end) from Medical_plan mp join Medications_for_plan mfp on mfp.id_medical_plan = mp.id join Medication m on mfp.id_medication = m.id\n" +
            "join Patient p on p.id = mp.id_patient join User u on u.id = p.id_user where u.username = :username")
    List<MedicalPlansDetail> findMedicalPlansForPatient(@Param("username") String username);
    @Query(value ="SELECT new ro.tuc.ds2020.entities.MedicalPlansDetail(m.id, m.name, m.dosage, mfp.start_period_tratament, mfp.end_period_tratament,mfp.intake_interval_start, mfp.intake_interval_end) from Medical_plan mp join Medications_for_plan mfp on mfp.id_medical_plan = mp.id join Medication m on m.id = mfp.id_medication join Patient p on p.id = mp.id_patient join User u on p.id_user = u.id")
    List<MedicalPlansDetail> getMedicationsForMedicalPlan();
}
