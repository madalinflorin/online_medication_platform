package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ro.tuc.ds2020.entities.PatientUser;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface PatientUserRepository extends JpaRepository<PatientUser, UUID> {


    @Transactional
    @Modifying
    @Query(value = "delete from users u using user_roles ur, roles r where ur.user_id = u.id and r.id = ur.role_id and u.username = :username and r.name = 'ROLE_PATIENT'",nativeQuery = true)
    void deleteByUsername(@Param("username") String username);


    @Query(value = "SELECT new ro.tuc.ds2020.entities.PatientUser(p.id, p.name, p.birth_date, p.gender, p.address, p.medical_record, p.id_user, u.username, u.email, u.password) from Patient p join User u on p.id_user = u.id and u.username = :username ")
    PatientUser findOnePatientUser(@Param("username") String username);

    @Query(value = "SELECT new ro.tuc.ds2020.entities.PatientUser(p.id, p.name, p.birth_date, p.gender, p.address, p.medical_record, p.id_user, u.username, u.email, u.password) \n" +
            "from Patient p join User u \n" +
            "on p.id_user = u.id \n" +
            "join Caregiver_patient cp \n" +
            "on p.id = cp.id_patient\n" +
            "join Caregiver c \n" +
            "on c.id = cp.id_caregiver\n" +
            "join User u1 \n" +
            "on c.id_user = u1.id\n" +
            "where u.username = :username and u1.username = :username1")
    PatientUser findOnePatientUserForCaregiver(@Param("username") String username, @Param("username1") String usernameCaregiver);


    @Query(value = "SELECT new ro.tuc.ds2020.entities.PatientUser(p.id, p.name, p.birth_date, p.gender, p.address, p.medical_record, p.id_user, u.username, u.email, u.password) from Patient p join User u on p.id_user = u.id and u.username = :username ")
    List<PatientUser> findOnePatientUser1(@Param("username") String username);

    @Query(value = "SELECT new ro.tuc.ds2020.entities.PatientUser(p.id, p.name, p.birth_date, p.gender, p.address, p.id_user, u.username, u.email, u.password) from Caregiver p join User u on p.id_user = u.id and u.username = :username ")
    PatientUser findOneCaregiverUser(@Param("username") String username);

    @Query(value = "SELECT new ro.tuc.ds2020.entities.PatientUser(p.id, p.name, p.birth_date, p.gender, p.address, p.id_user, u.username, u.email, u.password) from Caregiver p join User u on p.id_user = u.id and u.username = :username ")
    List<PatientUser> findOneCaregiverUser1(@Param("username") String username);


    @Query(value = "SELECT new ro.tuc.ds2020.entities.PatientUser(p.id, p.name, p.birth_date, p.gender, p.address, p.medical_record, p.id_user, u.username, u.email, u.password) from Patient p join User u on p.id_user = u.id ")
    List<PatientUser> findAllPacientsUsers();

    @Query(value = "select p.id, p.name, p.birth_date, p.gender, p.address, p.medical_record, p.id_user, u.username, u.email, u.password from patient p join caregiver_patient cp on p.id = cp.id_patient join caregiver c on cp.id_caregiver = c.id join users u on c.id_user=u.id where u.username = :username",nativeQuery = true)
    List<PatientUser> findAllPacientsUsersForCaregiver(@Param("username") String username);


    @Query(value = "SELECT new ro.tuc.ds2020.entities.PatientUser(p.id, p.name, p.birth_date, p.gender, p.address, p.id_user, u.username, u.email, u.password) from Caregiver p join User u on p.id_user = u.id ")
    List<PatientUser> findAllCaregiversUsers();

    @Transactional
    @Modifying
    @Query(value = "UPDATE User set email = :email,password = :password where username = :username")
    void updateUser(@Param("username")String username,@Param("email") String email,@Param("password") String password);


    @Transactional
    @Modifying
    @Query(value ="update Patient set name = :name, birth_date = :birth_date, gender = :gender, address = :address, medical_record = :medical_record where id_user = :id_user")
    void updatePatient(@Param("id_user") long id_user, @Param("name") String name, @Param("birth_date") Date birth_date, @Param("gender") char gender,@Param("address") String address, @Param("medical_record") String medical_record);

    @Transactional
    @Modifying
    @Query(value ="update Caregiver set name = :name, birth_date = :birth_date, gender = :gender, address = :address where id_user = :id_user")
    void updateCaregiver(@Param("id_user") long id_user, @Param("name") String name, @Param("birth_date") Date birth_date, @Param("gender") char gender,@Param("address") String address);


    @Query(value = "select c.id from Caregiver c join User u on u.id = c.id_user where u.username= :username_caregiver")
    UUID extrageCaregiver(@Param("username_caregiver") String username_caregiver);

    @Query(value = "select p.id from Patient p join User u on u.id = p.id_user where u.username= :username_patient")
    UUID extragePatient(@Param("username_patient") String username_patient);


    @Transactional
    @Modifying
    @Query(value = "insert into caregiver_patient values (:id1,:id2)",nativeQuery = true)
    void faLegatura(@Param("id1") byte[] id1,@Param("id2") byte[] id2);


    @Query(value = "select p.id from Patient p join User u on p.id_user = u.id and u.username = :username")
    UUID searchIdByUsername(@Param("username") String username);



}
