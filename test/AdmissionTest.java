/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Admission.DatabaseUtils;
import Admission.Patient;
import Admission.Services;
import Admission.Venue;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import static org.junit.Assert.*;
import org.junit.Test;

public class AdmissionTest {
    
    static EntityManagerFactory fact;

       public void clean() {
        Services serv = new Services(DatabaseUtils.factTest());
        serv.removeAllPatient();
        serv.removeAllVenue();
        List<Venue> res = serv.getAllVenue();
        assert(res.isEmpty());
    }
       
    @Test
    public void patient(){
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
        Patient pat = serv.newPatient(111111111, "nom1", "prenom1", "dateNaiss1", "adresse1", "phone1", "numSS1");
        assertNotNull(pat); 
        pat = serv.newPatient(22222222, "nom2", "prenom2", "dateNaiss2", "adresse2", "phone2", "numSS2");
        assertNotNull(pat);
        pat = serv.newPatient(333333333, "nom3", "prenom3", "dateNaiss3", "adresse3", "phone3", "numSS3");
        assertNotNull(pat);
        List<Patient> res = (List<Patient>) serv.getPatientByIPP(333333333);
        assert(!res.isEmpty());
        assert(res.size() == 1);
      
        res = serv.getAllPatient();
        assert(!res.isEmpty());
        assert(res.size() == 3);
        
    }
}
