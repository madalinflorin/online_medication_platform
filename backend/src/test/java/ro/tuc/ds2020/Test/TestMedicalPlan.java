package ro.tuc.ds2020.Test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import ro.tuc.ds2020.dtos.MedicalPlansDetailDTO;
import ro.tuc.ds2020.entities.Medical_plan;
import ro.tuc.ds2020.services.MedicalPlanService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class TestMedicalPlan {

    private final UUID uuid;

    private final String name;

    private float dosage;

    private final String start_period_tratament;

    private final String end_period_tratament;

    private final String intake_interval_start;

    private final String intake_interval_end;

    private final String username;

    @Mock
    MedicalPlanService medicalPlanService;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    public TestMedicalPlan(String uuid, String name,float dosage, String start_period_tratament, String end_period_tratament, String intake_interval_start, String intake_interval_end,String username) {
        this.uuid = UUID.fromString(uuid);
        this.name = name;
        this.dosage = dosage;
        this.start_period_tratament = start_period_tratament;
        this.end_period_tratament = end_period_tratament;
        this.intake_interval_start = intake_interval_start;
        this.intake_interval_end = intake_interval_end;
        this.username = username;
    }




    @Parameterized.Parameters
    public static Collection methodForMedicationDTO() {
        return Arrays.asList(new Object[][] {
                {"1e3e4380-c255-48a2-97a3-2822d678da7b", "Parasinus", (float) 100.2, "2020-01-01","2020-02-02","11:30","13:30","patient"},
                {"1e3e4380-c255-48a2-97a3-2822d678da7b", "Parasinus", 0, "2020-01-01","2020-02-02","11:30","13:30","patient"},
                {"ce0e4380-d655-48a2-97a3-9822d67bba7b", "Nurofen", 210, "2020-01-01","2020-02-02","11:30","13:30","patient"},
                {"de0e4380-1c55-48a2-97a3-1822d67cda7b", "Algocalmin", 300, "2020-01-01","2020-02-02","11:30","13:30","patient"},
        });
    }

    public void setUp(){

    }

    @Test
    public void testInsertMedicalPlan(){

        when(medicalPlanService.insert(username)).thenReturn(uuid);
        Assert.assertEquals(uuid, medicalPlanService.insert(username));
        verify(medicalPlanService).insert(username);

    }

    @Test
    public void testListMedicalPlans(){

        List<Medical_plan> list = new ArrayList<>();
        when(medicalPlanService.findMedicalPlans()).thenReturn(list);
        Assert.assertEquals(list, medicalPlanService.findMedicalPlans());
        verify(medicalPlanService).findMedicalPlans();

    }

    @Test
    public void insertMedicationsToMedicalPlan(){

        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = null;
        Date dateFormat1 = null;
        try {
            dateFormat = formatter.parse(start_period_tratament);
            dateFormat1 = formatter.parse(end_period_tratament);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        MedicalPlansDetailDTO medicalPlansDetailDTO = new MedicalPlansDetailDTO(uuid,name,dosage,dateFormat,dateFormat1,intake_interval_start,intake_interval_end);
        when(medicalPlanService.insertMedicationsToMedicalPlan(medicalPlansDetailDTO)).thenReturn(uuid);
        Assert.assertEquals(uuid, medicalPlanService.insertMedicationsToMedicalPlan(medicalPlansDetailDTO));
        verify(medicalPlanService).insertMedicationsToMedicalPlan(medicalPlansDetailDTO);

    }

    @Test
    public void testFindMedicationsForPatient(){

        List<MedicalPlansDetailDTO> medicalPlansDetailDTOS = new ArrayList<>();
        when(medicalPlanService.findMedicalPlansForPatient(username)).thenReturn(medicalPlansDetailDTOS);
        Assert.assertEquals(medicalPlansDetailDTOS, medicalPlanService.findMedicalPlansForPatient(username));
        verify(medicalPlanService).findMedicalPlansForPatient(username);

    }


}
