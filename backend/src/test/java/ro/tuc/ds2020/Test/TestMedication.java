package ro.tuc.ds2020.Test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.services.MedicationService;

import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class TestMedication  {


    private final UUID uuid;
    private final String name;
    private final String list_of_effects;
    private final float dosage;

    @Mock
    MedicationService medicationService;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    public TestMedication(String uuid, String name,float dosage, String list_of_effects) {
        this.uuid = UUID.fromString(uuid);
        this.name = name;
        this.dosage = dosage;
        this.list_of_effects = list_of_effects;
    }




    @Parameterized.Parameters
    public static Collection methodForMedicationDTO() {
        return Arrays.asList(new Object[][] {
                {"1e3e4380-c255-48a2-97a3-2822d678da7b", "Parasinus", (float) 100.2, "Dureri musculare,Greata"},
                {"1e3e4380-c255-48a2-97a3-2822d678da7b", "Parasinus", 0, "Dureri musculare,Greata"},
                {"ce0e4380-d655-48a2-97a3-9822d67bba7b", "Nurofen", 210, "Dureri articulare,Oboseala"},
                {"de0e4380-1c55-48a2-97a3-1822d67cda7b", "Algocalmin", 300, "Somnolenta"}
        });
    }

    public void setUp(){

    }

    @Test
    public void testFindMedicationName(){

            List<MedicationDTO> medicationDTOList = new ArrayList<>();
            MedicationDTO medicationDTO = new MedicationDTO(uuid, name, dosage, list_of_effects);
            medicationDTOList.add(medicationDTO);
            when(medicationService.findMedicationById(uuid)).thenReturn(medicationDTOList);
            Assert.assertEquals(name, medicationService.findMedicationById(uuid).get(0).getName());
            verify(medicationService).findMedicationById(uuid);

    }

    @Test
    public void testFindMedications(){
        List<MedicationDTO> medicationDTOList = new ArrayList<>();
        MedicationDTO medicationDTO =new MedicationDTO(uuid,name, dosage,list_of_effects);
        medicationDTOList.add(medicationDTO);
        medicationDTO =new MedicationDTO(uuid,name, dosage,list_of_effects);
        medicationDTOList.add(medicationDTO);
        when(medicationService.findMedications()).thenReturn(medicationDTOList);
        Assert.assertEquals(medicationDTOList,medicationService.findMedications());
        verify(medicationService).findMedications();

    }


    @Test
    public void testMedicationSideEffects(){
        List<MedicationDTO> medicationDTOList = new ArrayList<>();
        MedicationDTO medicationDTO =new MedicationDTO(uuid,name, dosage,list_of_effects);
        medicationDTOList.add(medicationDTO);
        when(medicationService.findMedicationById(uuid)).thenReturn(medicationDTOList);
        Assert.assertEquals(list_of_effects,medicationService.findMedicationById(uuid).get(0).getList_of_effects());
        verify(medicationService).findMedicationById(uuid);
    }
    @Test
    public void testMedicationDosage(){
        List<MedicationDTO> medicationDTOList = new ArrayList<>();
        MedicationDTO medicationDTO =new MedicationDTO(uuid,name, dosage,list_of_effects);
        medicationDTOList.add(medicationDTO);
        when(medicationService.findMedicationById(uuid)).thenReturn(medicationDTOList);
        Assert.assertEquals(dosage,medicationService.findMedicationById(uuid).get(0).getDosage(),0.01);
        verify(medicationService).findMedicationById(uuid);
    }


    @Test
    public void testInsertMedication(){
        MedicationDTO medicationDTO =new MedicationDTO(uuid,name, dosage,list_of_effects);
        when(medicationService.insert(medicationDTO)).thenReturn(uuid);
        Assert.assertEquals(uuid,medicationService.insert(medicationDTO));
        verify(medicationService).insert(medicationDTO);
    }

    @Test

    public void testDosageLessThanZero(){


        try{
            new MedicationDTO(uuid, name, dosage, list_of_effects);
        }
        catch (NumberFormatException e){

            Assert.assertTrue(dosage<=0);


        }
        Assert.assertTrue(dosage>0);


    }


    @Test

    public void testDeleteMedication(){

        when(medicationService.deleteMedicationById(uuid)).thenReturn(true);
        Assert.assertTrue(medicationService.deleteMedicationById(uuid));
        verify(medicationService).deleteMedicationById(uuid);

    }

    @Test

    public void testUpdateMedication(){

        MedicationDTO medicationDTO =new MedicationDTO(uuid,name, dosage,list_of_effects);
        when(medicationService.updateMedication(medicationDTO)).thenReturn(uuid);
        Assert.assertEquals(uuid,medicationService.updateMedication(medicationDTO));
        verify(medicationService).updateMedication(medicationDTO);


    }


}