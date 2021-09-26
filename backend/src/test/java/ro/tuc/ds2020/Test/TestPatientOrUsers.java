package ro.tuc.ds2020.Test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import ro.tuc.ds2020.dtos.PatientDetailsDTO;
import ro.tuc.ds2020.dtos.PatientUserDTO;
import ro.tuc.ds2020.services.PatientService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)

public class TestPatientOrUsers {

    private final UUID uuid;
    private final String name;
    private final String date;
    private final char gender;
    private final String address;
    private final long id_user;
    private final String medical_record;
    private final String username;
    private final String email;
    private final String password;

    @Mock
    PatientService patientService;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();


    public TestPatientOrUsers(String uuid, String name, String date, char gender, String address, long id_user,String medical_record,String username, String email, String password){
        this.uuid = UUID.fromString(uuid);
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.address = address;
        this.id_user = id_user;
        this.medical_record = medical_record;
        this.username = username;
        this.email = email;
        this.password = password;

    }

    @Parameterized.Parameters
    public static Collection methodForCaregiverDetailsDTO() {
        return Arrays.asList(new Object[][] {
                {"1e3e4380-c255-48a2-97a3-2822d678da7b", "George", "1998-04-04",'M',"Str.Narciselor",64,"Cancer","patient1","email1@gmail.com","parola1"},
                {"1e3e4780-c255-48a2-97a3-2822d678da7b", "Flavius", "1998-04-04", 'M',"Str.Narciselor",65,"Cancer","patient2","email2@gmail.com","parola2"},
                {"ce0e4380-d655-48a2-97a3-9822d67bba7b", "Andrei", "1998-04-04", 'M',"Str.Narciselor",66,"Cancer","patient3","email3@gmail.com","parola3"},
                {"de0e4380-1c55-48a2-97a3-1822d67cda7b", "Ovidiu", "1998-04-04", 'M',"Str.Narciselor",67,"Cancer","patient4","email4@gmail.com","parola4"},
        });
    }


    @Before
    public void setUp(){

    }
    @Test
    public void testPatientName(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        when(patientService.findPatientById(uuid)).thenReturn(new PatientDetailsDTO(uuid,name, dateFormat,gender,address,medical_record, id_user));
        Assert.assertEquals(name,patientService.findPatientById(uuid).getName());
        verify(patientService).findPatientById(uuid);
    }
    @Test
    public void testPatientDate(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat1 = null;
        try {
            dateFormat1 = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        when(patientService.findPatientById(uuid)).thenReturn(new PatientDetailsDTO(uuid,name, dateFormat1,gender,address,medical_record, id_user));
        Assert.assertEquals(date,dateFormat.format(patientService.findPatientById(uuid).getBirth_date()));
        verify(patientService).findPatientById(uuid);
    }

    @Test
    public void testPatientGender(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        when(patientService.findPatientById(uuid)).thenReturn(new PatientDetailsDTO(uuid,name, dateFormat,gender,address,medical_record, id_user));
        Assert.assertEquals(gender,patientService.findPatientById(uuid).getGender());
        verify(patientService).findPatientById(uuid);
    }

    @Test
    public void testPatientAddress(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        when(patientService.findPatientById(uuid)).thenReturn(new PatientDetailsDTO(uuid,name, dateFormat,gender,address,medical_record, id_user));
        Assert.assertEquals(address,patientService.findPatientById(uuid).getAddress());
        verify(patientService).findPatientById(uuid);
    }

    @Test
    public void testPatientIdUser(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        when(patientService.findPatientById(uuid)).thenReturn(new PatientDetailsDTO(uuid,name, dateFormat,gender,address,medical_record, id_user));
        Assert.assertEquals(id_user,patientService.findPatientById(uuid).getId_user());
        verify(patientService).findPatientById(uuid);
    }


    @Test
    public void testInsertPatient(){

        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PatientDetailsDTO patientDetailsDTO = new PatientDetailsDTO(uuid,name, dateFormat,gender,address,medical_record, id_user);
        when(patientService.insert(patientDetailsDTO)).thenReturn(uuid);
        Assert.assertEquals(uuid,patientService.insert(patientDetailsDTO));
        verify(patientService).insert(patientDetailsDTO);
    }

    @Test
    public void testFindPatients(){

        List<PatientUserDTO> patientUserDTOList = new ArrayList<>();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PatientUserDTO patientUserDTO = new PatientUserDTO(uuid,name, dateFormat,gender,id_user,username,email,password);
        patientUserDTOList.add(patientUserDTO);
        patientUserDTO = new PatientUserDTO(uuid, name, dateFormat,gender,id_user,username,email,password);
        patientUserDTOList.add(patientUserDTO);
        when(patientService.findPatientsUsers()).thenReturn(patientUserDTOList);
        Assert.assertEquals(patientUserDTOList,patientService.findPatientsUsers());
        verify(patientService).findPatientsUsers();
    }


    @Test
    public void testFindPatientById(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        when(patientService.findPatientById(uuid)).thenReturn(new PatientDetailsDTO(uuid,name, dateFormat,gender,address,medical_record,id_user));
        Assert.assertEquals(uuid,patientService.findPatientById(uuid).getId());
        verify(patientService).findPatientById(uuid);
    }


    @Test
    public void testFindCaregivers(){

        List<PatientUserDTO> caregiverUserDTOList = new ArrayList<>();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PatientUserDTO caregiverUserDTO = new PatientUserDTO(uuid,name, dateFormat,gender,id_user,username,email,password);
        caregiverUserDTOList.add(caregiverUserDTO);
        caregiverUserDTO = new PatientUserDTO(uuid,name, dateFormat,gender,id_user,username,email,password);
        caregiverUserDTOList.add(caregiverUserDTO);
        when(patientService.findCaregiversUsers()).thenReturn(caregiverUserDTOList);
        Assert.assertEquals(caregiverUserDTOList,patientService.findCaregiversUsers());
        verify(patientService).findCaregiversUsers();
    }

    @Test
    public void testDeleteUser(){

        when(patientService.deleteUser(username)).thenReturn(true);
        Assert.assertTrue(patientService.deleteUser(username));
        verify(patientService).deleteUser(username);
    }

    @Test
    public void testUpdatePatientUser(){

        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PatientUserDTO patientUserDTO = new PatientUserDTO(uuid,name, dateFormat,gender,id_user,username,email,password);
        when(patientService.updatePatient(patientUserDTO)).thenReturn(uuid);
        Assert.assertEquals(uuid,patientService.updatePatient(patientUserDTO));
        verify(patientService).updatePatient(patientUserDTO);
    }


    @Test
    public void testUpdateCaregiverUser(){

        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PatientUserDTO caregiverUserDTO = new PatientUserDTO(uuid,name, dateFormat,gender,id_user,username,email,password);
        when(patientService.updateCaregiver(caregiverUserDTO)).thenReturn(uuid);
        Assert.assertEquals(uuid,patientService.updateCaregiver(caregiverUserDTO));
        verify(patientService).updateCaregiver(caregiverUserDTO);
    }

    @Test
    public void testGetUsernameWithId(){

        when(patientService.getUsername(uuid)).thenReturn(username);
        Assert.assertEquals(username,patientService.getUsername(uuid));
        verify(patientService).getUsername(uuid);
    }

    @Test
    public void testFindPatientsUsersForCaregiver(){

        List<PatientUserDTO> patientUserDTOList = new ArrayList<>();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PatientUserDTO patientUserDTO = new PatientUserDTO(uuid,name, dateFormat,gender,id_user,username,email,password);
        patientUserDTOList.add(patientUserDTO);
        patientUserDTO = new PatientUserDTO(uuid, name, dateFormat,gender,id_user,username,email,password);
        patientUserDTOList.add(patientUserDTO);
        when(patientService.findPatientsUsersForCaregiver("caregiver")).thenReturn(patientUserDTOList);
        Assert.assertEquals(patientUserDTOList,patientService.findPatientsUsersForCaregiver("caregiver"));
        verify(patientService).findPatientsUsersForCaregiver("caregiver");
    }

}
