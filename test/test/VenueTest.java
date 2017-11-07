/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Admission.DatabaseUtils;
import Admission.Services;
import Admission.Venue;
import Admission.Patient;
import java.util.ArrayList;
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
        serv.removeAllVenue(); 
        serv.removeAllPatient();       
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
        List<Venue> ven = new ArrayList();
        for (int i=0; i<3; i++) {
            Venue v = new Venue();
            v.setPatient(serv.findPatient("nom1", "prenom1", "dateNaiss1").get(0));
            v.setDateVenue("dateVenue"+i);
            v.setDateSortie("dateSortie"+i);
            v.setTypeVenue("typeVenue"+i);
            v.setUFtraitement("UFtraitement"+i);
            serv.newVenue(v);
            ven.add(v);
        }
        assert(serv.findVenue(ven.get(0).getPatient().getIPP(),ven.get(0).getIEP(), "dateVenue0").size() == 1);
    }
    
    @Test
    public void getVenuePatientTest(){
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
        Patient pat1 = serv.newPatient("nom1", "prenom1", "dateNaiss1", "adresse1", "phone1", "numSS1", "sexe1");
        Patient pat2 = serv.newPatient("nom2", "prenom2", "dateNaiss2", "adresse2", "phone2", "numSS2", "sexe2");
        for (int i=0; i<3; i++) {
            serv.newVenue(pat1, "dateVenue" + i, "dateSortie"+i, "UFtraitement"+i, "typeVenue"+i);
        }
        for (int i=3; i<6; i++) {
            serv.newVenue(pat2, "dateVenue" + i, "dateSortie"+i, "UFtraitement"+i, "typeVenue"+i);
        }
        assert(serv.getVenuePatient(pat1).size()==3);
        assert(serv.getVenuePatient(pat2).size()==3);
        
    }
}
