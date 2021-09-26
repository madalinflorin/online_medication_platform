package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Patient;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

    @Query(value = "select u.username from Patient p join User u on p.id_user = u.id where p.id = :id")
    public String findUsername(@Param("id") UUID id);

}
