package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ro.tuc.ds2020.entities.Caregiver;

import java.util.List;
import java.util.UUID;

public interface CaregiverRepository extends JpaRepository<Caregiver, UUID> {

    List<Caregiver> findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "delete from users u using user_roles ur, roles r where ur.user_id = u.id and r.id = ur.role_id and u.username = :username and r.name = 'ROLE_CAREGIVER'",nativeQuery = true)
    void deleteCaregiverByUsername(@Param("username") String username);

}
