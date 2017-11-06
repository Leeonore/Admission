/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Admission.DatabaseUtils;
import Admission.Services;
import Admission.Venue;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Nicolas
 */
public class VenueTest {
     static EntityManagerFactory fact;

    public VenueTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        
    }

    @AfterClass
    public static void tearDownClass() {
        
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        
    }

    public void clean() {
        Services serv = new Services(DatabaseUtils.factTest());
        serv.removeAllPatient();
        serv.removeAllVenue();
        List<Venue> res = serv.getAllVenue();
        assert (res.isEmpty());
    }
    
    @Test
    public void addOneVenueTest() {
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
        serv.newVenue(serv.newPatient("nom1", "prenom1", "dateNaiss1", "adresse1", "phone1", "numSS1", "sexe1"), "dateVenue1", "dateSortie1", "UFtraitement1", "typeVenue1");
        assert(!serv.getAllVenue().isEmpty());
        assert(serv.getAllVenue().size() == 1);
        
        Venue v = serv.getAllVenue().get(0);
        assert(v.getDateSortie().equals("dateSortie1"));
        assert(v.getDateVenue().equals("dateVenue1"));
        assert(v.getPatient().getNom().equals("nom1"));
        assert(v.getUFtraitement().equals("UFtraitement1"));
        assert(v.getTypeVenue().equals("typeVenue1"));
    }
    
    @Test
    public void addOneVenueByInfoTest() {
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
        serv.newPatient("nom1", "prenom1", "dateNaiss1", "adresse1", "phone1", "numSS1", "sexe1");
        Venue v = new Venue();
        v.setPatient(serv.findPatient("nom1", "prenom1", "dateNaiss1").get(0));
        v.setDateVenue("dateVenue1");
        v.setDateSortie("dateSortie1");
        v.setTypeVenue("typeVenue1");
        v.setUFtraitement("UFtraitement1");
        serv.newVenue(v);
        
        assert(!serv.getAllVenue().isEmpty());
        assert(serv.getAllVenue().size() == 1);
        assert(serv.getAllVenue().get(0).getPatient().getNom().equals("nom1"));
    }
    
    @Test
    public void addMultipleVenueOnePatient() {
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
        serv.newPatient("nom1", "prenom1", "dateNaiss1", "adresse1", "phone1", "numSS1", "sexe1");
        for (int i=0; i<3; i++) {
            Venue v = new Venue();
            v.setPatient(serv.findPatient("nom1", "prenom1", "dateNaiss1").get(0));
            v.setDateVenue("dateVenue"+i);
            v.setDateSortie("dateSortie"+i);
            v.setTypeVenue("typeVenue"+i);
            v.setUFtraitement("UFtraitement"+i);
            serv.newVenue(v);
        }
        assert(serv.getAllVenue().size() == 3);
        // Test que chaque venue concerne le même patient
        for (int i=0; i<3; i++) {
            assert(serv.getAllVenue().get(i).getPatient().getNom().equals("nom1"));
        }
    }
    
    @Test
    public void deleteOneVenueByIEPTest() {
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
        serv.newPatient("nom1", "prenom1", "dateNaiss1", "adresse1", "phone1", "numSS1", "sexe1");
        Venue v = new Venue();
        v.setPatient(serv.findPatient("nom1", "prenom1", "dateNaiss1").get(0));
        v.setDateVenue("dateVenue1");
        v.setDateSortie("dateSortie1");
        v.setTypeVenue("typeVenue1");
        v.setUFtraitement("UFtraitement1");
        serv.newVenue(v);
        
        assert(serv.getAllVenue().size() == 1);
        serv.removeVenue(serv.getAllVenue().get(0).getIEP());
        assert(serv.getAllVenue().isEmpty());
    }
    
    @Test
    public void editDateSortieTest() {
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
        serv.newVenue(serv.newPatient("nom1", "prenom1", "dateNaiss1", "adresse1", "phone1", "numSS1", "sexe1"), "dateVenue1", "", "UFtraitement1", "typeVenue1");
        assert(serv.getAllVenue().get(0).getDateSortie().equals(""));
        
        Venue v = serv.getAllVenue().get(0);
        v.setDateSortie("dateSortie1");
        serv.editVenue(v);
        assert(serv.getAllVenue().get(0).getDateSortie().equals("dateSortie1"));
    }
    
    @Test
    public void deleteAllVenueTest() {
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
        serv.newPatient("nom1", "prenom1", "dateNaiss1", "adresse1", "phone1", "numSS1", "sexe1");
        for (int i=0; i<3; i++) {
            Venue v = new Venue();
            v.setPatient(serv.findPatient("nom1", "prenom1", "dateNaiss1").get(0));
            v.setDateVenue("dateVenue"+i);
            v.setDateSortie("dateSortie"+i);
            v.setTypeVenue("typeVenue"+i);
            v.setUFtraitement("UFtraitement"+i);
            serv.newVenue(v);
        }
        serv.removeAllVenue();
        assert(serv.getAllVenue().isEmpty());
    }
    
    @Test
    public void findVenueTest() {
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
        serv.newPatient("nom1", "prenom1", "dateNaiss1", "adresse1", "phone1", "numSS1", "sexe1");
        for (int i=0; i<3; i++) {
            Venue v = new Venue();
            v.setPatient(serv.findPatient("nom1", "prenom1", "dateNaiss1").get(0));
            v.setDateVenue("dateVenue"+i);
            v.setDateSortie("dateSortie"+i);
            v.setTypeVenue("typeVenue"+i);
            v.setUFtraitement("UFtraitement"+i);
            serv.newVenue(v);
        }
        /*System.out.println("testtest" + serv.getAllPatient().get(0));
        System.out.println("testtest2" + serv.getAllPatient().get(0).getIPP());
        System.out.println("testtest3" + serv.getAllVenue());
        System.out.println("testtest4" + serv.getAllVenue().get(0).getIEP());
        System.out.println("testtest4" + serv.findVenue(serv.getAllPatient().get(0).getIPP(), serv.getAllVenue().get(0).getIEP(), serv.getAllVenue().get(0).getDateVenue()));*/
        //assert(serv.findVenue(serv.getAllPatient().get(0).getIPP(), serv.getAllVenue().get(0).getIEP(), serv.getAllVenue().get(0).getDateVenue()).size() == 1);
        assert(serv.findVenue(1, 1, "dateVenue0").size() == 1);
    }
}
