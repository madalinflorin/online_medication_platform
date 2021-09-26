package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.PatientActivity;
import ro.tuc.ds2020.entities.PatientActivitySuspicious;

import java.util.List;
import java.util.UUID;

public interface PatientActivityRepository extends JpaRepository<PatientActivity, UUID> {




    @Query(value = "SELECT new ro.tuc.ds2020.entities.PatientActivitySuspicious(u1.username, pa.activity, pa.start_date, pa.end_date) from PatientActivity pa join Patient p on p.id = pa.id_patient join Caregiver_patient cp on p.id = cp.id_patient join Caregiver c on c.id = cp.id_caregiver join User u on u.id = c.id_user join User u1 on p.id_user = u1.id where u.username = :username")
    List<PatientActivitySuspicious> findPatientActivityByUsername(@Param("username") String username);

}
