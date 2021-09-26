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
import ro.tuc.ds2020.dtos.CaregiverDetailsDTO;
import ro.tuc.ds2020.services.CaregiverService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class TestCaregiver {


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
    CaregiverService caregiverService;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();


    public TestCaregiver(String uuid, String name, String date, char gender, String address, long id_user,String medical_record,String username, String email, String password){
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
    public void testCaregiverName(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        when(caregiverService.findCaregiverById(uuid)).thenReturn(new CaregiverDetailsDTO(uuid,name, dateFormat,gender,address,id_user));
        Assert.assertEquals(name,caregiverService.findCaregiverById(uuid).getName());
        verify(caregiverService).findCaregiverById(uuid);
    }
    @Test
    public void testCaregiverDate(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateType = null;
        try {
            dateType = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        when(caregiverService.findCaregiverById(uuid)).thenReturn(new CaregiverDetailsDTO(uuid,name, dateType,gender,address,id_user));
        Assert.assertEquals(date,dateFormat.format(caregiverService.findCaregiverById(uuid).getBirth_date()));
        verify(caregiverService).findCaregiverById(uuid);
    }
    @Test
    public void testCaregiverGender(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        when(caregiverService.findCaregiverById(uuid)).thenReturn(new CaregiverDetailsDTO(uuid,name, dateFormat,gender,address,id_user));
        Assert.assertEquals(gender,caregiverService.findCaregiverById(uuid).getGender());
        verify(caregiverService).findCaregiverById(uuid);
    }

    @Test
    public void testCaregiverAddress(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        when(caregiverService.findCaregiverById(uuid)).thenReturn(new CaregiverDetailsDTO(uuid,name, dateFormat,gender,address,id_user));
        Assert.assertEquals(address,caregiverService.findCaregiverById(uuid).getAddress());
        verify(caregiverService).findCaregiverById(uuid);
    }

    @Test
    public void testCaregiverIdUser(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        when(caregiverService.findCaregiverById(uuid)).thenReturn(new CaregiverDetailsDTO(uuid,name, dateFormat,gender,address,id_user));
        Assert.assertEquals(id_user,caregiverService.findCaregiverById(uuid).getId_user());
        verify(caregiverService).findCaregiverById(uuid);
    }

    @Test
    public void testInsertCaregiver(){
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        try {
            dateFormat = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CaregiverDetailsDTO caregiverDetailsDTO = new CaregiverDetailsDTO(name, dateFormat,gender,address,id_user);
        when(caregiverService.insert(caregiverDetailsDTO)).thenReturn(uuid);
        Assert.assertEquals(uuid,caregiverService.insert(caregiverDetailsDTO));
        verify(caregiverService).insert(caregiverDetailsDTO);
    }


    @Test
    public void testDeleteCaregiver(){
        when(caregiverService.deleteCaregiverByUsername(username)).thenReturn(true);
        Assert.assertTrue(caregiverService.deleteCaregiverByUsername(username));
    }


}
