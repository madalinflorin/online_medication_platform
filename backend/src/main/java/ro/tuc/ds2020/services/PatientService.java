package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.dtos.builders.PatientUserBuilder;
import ro.tuc.ds2020.entities.*;
import ro.tuc.ds2020.repositories.*;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;
    private final PatientUserRepository patientUserRepository;
    private final UserRepository userRepository;
    private final MedicalPlansDetailRepository  medicalPlansDetailRepository;
    private final MedicalPlanRepository medicalPlanRepository;
    private final CaregiverPatientRepository caregiverPatientRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public PatientService(PatientRepository patientRepository,PatientUserRepository patientUserRepository,UserRepository userRepository,MedicalPlansDetailRepository medicalPlansDetailRepository,MedicalPlanRepository medicalPlanRepository,CaregiverPatientRepository caregiverPatientRepository) {
        this.patientRepository = patientRepository;
        this.patientUserRepository = patientUserRepository;
        this.userRepository = userRepository;
        this.medicalPlansDetailRepository = medicalPlansDetailRepository;
        this.medicalPlanRepository = medicalPlanRepository;
        this.caregiverPatientRepository = caregiverPatientRepository;
    }

    public List<PatientDTO> findPatients() {
        List<Patient> patientList = patientRepository.findAll();
        return patientList.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }

    public List<PatientUserDTO> findPatientsUsers() {

        List<PatientUser> patientList = patientUserRepository.findAllPacientsUsers();
        return patientList.stream()
                .map(PatientUserBuilder::toPatientUserDTO)
                .collect(Collectors.toList());
    }

    public List<PatientUserDTO> findPatientsUsersForCaregiver(String user) {

        List<PatientUser> patientList = patientUserRepository.findAllPacientsUsersForCaregiver(user);
        return patientList.stream()
                .map(PatientUserBuilder::toPatientUserDTO)
                .collect(Collectors.toList());
    }


    public PatientUserDTO findPatientUser(String username) {

        PatientUser patientList = patientUserRepository.findOnePatientUser(username);
        return PatientUserBuilder.toPatientUserDTO(patientList);

    }

    public boolean deleteUser(String username){
        patientUserRepository.deleteByUsername(username);
        return true;
    }

    public List<PatientUserDTO> findCaregiversUsers() {

        List<PatientUser> patientList = patientUserRepository.findAllCaregiversUsers();
        return patientList.stream()
                .map(PatientUserBuilder::toCaregiverUserDTO)
                .collect(Collectors.toList());
    }

    public PatientUserDTO findCaregiverUser(String username) {

        PatientUser patientList = patientUserRepository.findOneCaregiverUser(username);
        return PatientUserBuilder.toPatientUserDTO(patientList);
    }


    public List<PatientUserDTO> findPatientUser1(String username) {

        List<PatientUser> patientList = patientUserRepository.findOnePatientUser1(username);
        return patientList.stream()
                .map(PatientUserBuilder::toPatientUserDTO)
                .collect(Collectors.toList());

    }

    public List<PatientUserDTO> findCaregiverUser1(String username) {

        List<PatientUser> patientList = patientUserRepository.findOneCaregiverUser1(username);
        return patientList.stream()
                .map(PatientUserBuilder::toPatientUserDTO)
                .collect(Collectors.toList());

    }

    public PatientDetailsDTO findPatientById(UUID id) {
        Optional<Patient> prosumerOptional = patientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found in db", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        return PatientBuilder.toPatientDetailsDTO(prosumerOptional.get());
    }


    public static UUID asUuid(byte[] bytes) {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        long firstLong = bb.getLong();
        long secondLong = bb.getLong();
        return new UUID(firstLong, secondLong);
    }

    public static byte[] asBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }


    public void makeOperations(CaregiverPatient caregiverPatient) {

        UUID id1 = patientUserRepository.extrageCaregiver(caregiverPatient.getUsername_caregiver());
        UUID id2 = patientUserRepository.extragePatient(caregiverPatient.getUsername_patient());


        if (id1 != null && id2 != null) {

            Caregiver_patient caregiverPatient1 = new Caregiver_patient(id2,id1);

            caregiverPatientRepository.save(caregiverPatient1);


        }

    }



    public UUID insert(PatientDetailsDTO patientDTO) {
        Patient patient = PatientBuilder.toEntity(patientDTO);
        patient = patientRepository.save(patient);
        LOGGER.debug("Patient with id {} was inserted in db", patient.getId());
        return patient.getId();
    }

    public UUID updatePatient(PatientUserDTO patientUserDTO){
        patientUserDTO.setPassword(encoder.encode(patientUserDTO.getPassword()));
        PatientUserDTO oldPatient = findPatientUser(patientUserDTO.getUsername());
        if(oldPatient!=null) {
            patientUserRepository.updateUser(patientUserDTO.getUsername(), patientUserDTO.getEmail(), patientUserDTO.getPassword());
            patientUserRepository.updatePatient(oldPatient.getId_user(), patientUserDTO.getName(), patientUserDTO.getBirth_date(), patientUserDTO.getGender(), patientUserDTO.getAddress(), patientUserDTO.getMedical_record());
            return oldPatient.getId();
        }
        return null;
    }


    public UUID updateCaregiver(PatientUserDTO patientUserDTO){
        patientUserDTO.setPassword(encoder.encode(patientUserDTO.getPassword()));

        PatientUserDTO oldPatient = findCaregiverUser(patientUserDTO.getUsername());

        if(oldPatient!=null) {
            patientUserRepository.updateUser(patientUserDTO.getUsername(), patientUserDTO.getEmail(), patientUserDTO.getPassword());
            patientUserRepository.updateCaregiver(oldPatient.getId_user(), patientUserDTO.getName(), patientUserDTO.getBirth_date(), patientUserDTO.getGender(), patientUserDTO.getAddress());
            return oldPatient.getId();
        }
        return null;
    }

    public String getUsername(UUID id){
        return patientRepository.findUsername(id);
    }

}
