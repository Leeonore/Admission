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
    
    public void IPPPatient(){
        Patient pat = new Patient();
        pat.setNom("nom1");
        pat.setAdresse("adresse1");
        pat.setDateNaiss("dateNaiss1");
        pat.setPrenom("prenom1");
        pat.setNumSecu("numsecu1");
        pat.setPhone("telephone1");
        pat.setSexe("sexe1");
    }
    
    @Test
    public void addOneVenueTest() {
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
       //Création du patient
        Patient pat = new Patient();
        pat.setNom("nom1");
        pat.setAdresse("adresse1");
        pat.setDateNaiss("dateNaiss1");
        pat.setPrenom("prenom1");
        pat.setNumSecu("numsecu1");
        pat.setPhone("telephone1");
        pat.setSexe("sexe1");
        serv.editPatient(pat);
       //Création d'une venue
        Venue v = new Venue();
        v.setPatient(serv.findPatient("nom1", "prenom1", "dateNaiss1").get(0));
        v.setDateVenue("dateVenue1");
        v.setDateSortie("dateSortie1");
        v.setTypeVenue("typeVenue1");
        v.setUFtraitement("UFtraitement1");
        serv.editVenue(v);
        
        assert(!serv.getAllVenue().isEmpty());
        assert(serv.getAllVenue().size() == 1);
        
        Venue v2 = serv.getAllVenue().get(0);
        assert(v2.getDateSortie().equals("dateSortie1"));
        assert(v2.getDateVenue().equals("dateVenue1"));
        assert(v2.getPatient().getNom().equals("nom1"));
        assert(v2.getUFtraitement().equals("UFtraitement1"));
        assert(v2.getTypeVenue().equals("typeVenue1"));
    }
    
    @Test
    public void addOneVenueByInfoTest() {
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
       //Création du patient
        Patient pat = new Patient();
        pat.setNom("nom1");
        pat.setAdresse("adresse1");
        pat.setDateNaiss("dateNaiss1");
        pat.setPrenom("prenom1");
        pat.setNumSecu("numsecu1");
        pat.setPhone("telephone1");
        pat.setSexe("sexe1");
        serv.editPatient(pat);
        Venue v = new Venue();
        v.setPatient(serv.findPatient("nom1", "prenom1", "dateNaiss1").get(0));
        v.setDateVenue("dateVenue1");
        v.setDateSortie("dateSortie1");
        v.setTypeVenue("typeVenue1");
        v.setUFtraitement("UFtraitement1");
        serv.editVenue(v);
        
        assert(!serv.getAllVenue().isEmpty());
        assert(serv.getAllVenue().size() == 1);
        assert(serv.getAllVenue().get(0).getPatient().getNom().equals("nom1"));
    }
    
    @Test
    public void addMultipleVenueOnePatient() {
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
       //Création du patient
        Patient pat = new Patient();
        pat.setNom("nom1");
        pat.setAdresse("adresse1");
        pat.setDateNaiss("dateNaiss1");
        pat.setPrenom("prenom1");
        pat.setNumSecu("numsecu1");
        pat.setPhone("telephone1");
        pat.setSexe("sexe1");
        serv.editPatient(pat);
        for (int i=0; i<3; i++) {
            Venue v = new Venue();
            v.setPatient(serv.findPatient("nom1", "prenom1", "dateNaiss1").get(0));
            v.setDateVenue("dateVenue"+i);
            v.setDateSortie("dateSortie"+i);
            v.setTypeVenue("typeVenue"+i);
            v.setUFtraitement("UFtraitement"+i);
            serv.editVenue(v);
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
       //Création du patient
        Patient pat = new Patient();
        pat.setNom("nom1");
        pat.setAdresse("adresse1");
        pat.setDateNaiss("dateNaiss1");
        pat.setPrenom("prenom1");
        pat.setNumSecu("numsecu1");
        pat.setPhone("telephone1");
        pat.setSexe("sexe1");
        serv.editPatient(pat);
        Venue v = new Venue();
        v.setPatient(serv.findPatient("nom1", "prenom1", "dateNaiss1").get(0));
        v.setDateVenue("dateVenue1");
        v.setDateSortie("dateSortie1");
        v.setTypeVenue("typeVenue1");
        v.setUFtraitement("UFtraitement1");
        serv.editVenue(v);
        
        assert(serv.getAllVenue().size() == 1);
        serv.removeVenue(serv.getAllVenue().get(0).getIEP());
        assert(serv.getAllVenue().isEmpty());
    }
    
    @Test
    public void editDateSortieTest() {
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
       //Création du patient
        Patient pat = new Patient();
        pat.setNom("nom1");
        pat.setAdresse("adresse1");
        pat.setDateNaiss("dateNaiss1");
        pat.setPrenom("prenom1");
        pat.setNumSecu("numsecu1");
        pat.setPhone("telephone1");
        pat.setSexe("sexe1");
        serv.editPatient(pat);
        Venue v = new Venue();
        v.setPatient(serv.findPatient("nom1", "prenom1", "dateNaiss1").get(0));
        v.setDateVenue("dateVenue1");
        v.setDateSortie("dateSortie1");
        v.setTypeVenue("typeVenue1");
        v.setUFtraitement("UFtraitement1");
        serv.editVenue(v);
        assert(serv.getAllVenue().get(0).getDateSortie().equals(""));
        
        Venue v2 = serv.getAllVenue().get(0);
        v2.setDateSortie("dateSortie1");
        serv.editVenue(v2);
        assert(serv.getAllVenue().get(0).getDateSortie().equals("dateSortie1"));
    }
    
    @Test
    public void deleteAllVenueTest() {
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
       //Création du patient
        Patient pat = new Patient();
        pat.setNom("nom1");
        pat.setAdresse("adresse1");
        pat.setDateNaiss("dateNaiss1");
        pat.setPrenom("prenom1");
        pat.setNumSecu("numsecu1");
        pat.setPhone("telephone1");
        pat.setSexe("sexe1");
        serv.editPatient(pat);
        for (int i=0; i<3; i++) {
            Venue v = new Venue();
            v.setPatient(serv.findPatient("nom1", "prenom1", "dateNaiss1").get(0));
            v.setDateVenue("dateVenue"+i);
            v.setDateSortie("dateSortie"+i);
            v.setTypeVenue("typeVenue"+i);
            v.setUFtraitement("UFtraitement"+i);
            serv.editVenue(v);
        }
        serv.removeAllVenue();
        assert(serv.getAllVenue().isEmpty());
    }
    
    @Test
    public void findVenueTest() {
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
       //Création du patient
        Patient pat = new Patient();
        pat.setNom("nom1");
        pat.setAdresse("adresse1");
        pat.setDateNaiss("dateNaiss1");
        pat.setPrenom("prenom1");
        pat.setNumSecu("numsecu1");
        pat.setPhone("telephone1");
        pat.setSexe("sexe1");
        serv.editPatient(pat);
        List<Venue> ven = new ArrayList();
        for (int i=0; i<3; i++) {
            Venue v = new Venue();
            v.setPatient(serv.findPatient("nom1", "prenom1", "dateNaiss1").get(0));
            v.setDateVenue("dateVenue"+i);
            v.setDateSortie("dateSortie"+i);
            v.setTypeVenue("typeVenue"+i);
            v.setUFtraitement("UFtraitement"+i);
            serv.editVenue(v);
            ven.add(v);
        }
        assert(serv.findVenue(ven.get(0).getPatient().getIPP(),ven.get(0).getIEP(), "dateVenue0").size() == 1);
    }
    
    @Test
    public void getVenuePatientTest(){
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
       //Création des patients
        Patient pat1 = new Patient();
        pat1.setNom("nom1");
        pat1.setAdresse("adresse1");
        pat1.setDateNaiss("dateNaiss1");
        pat1.setPrenom("prenom1");
        pat1.setNumSecu("numsecu1");
        pat1.setPhone("telephone1");
        pat1.setSexe("sexe1");
        serv.editPatient(pat1);
        Patient pat2 = new Patient();
        pat2.setNom("nom2");
        pat2.setAdresse("adresse2");
        pat2.setDateNaiss("dateNaiss2");
        pat2.setPrenom("prenom2");
        pat2.setNumSecu("numsecu2");
        pat2.setPhone("telephone2");
        pat2.setSexe("sexe2");
        serv.editPatient(pat2);
        Venue v = new Venue();
        for (int i=0; i<3; i++) {
            v.setPatient(serv.findPatient("nom1", "prenom1", "dateNaiss1").get(0));
            v.setDateVenue("dateVenue"+i);
            v.setDateSortie("dateSortie"+i);
            v.setTypeVenue("typeVenue"+i);
            v.setUFtraitement("UFtraitement"+i);
            serv.editVenue(v);
        }
        for (int i=3; i<6; i++) {
            v.setPatient(serv.findPatient("nom2", "prenom2", "dateNaiss2").get(0));
            v.setDateVenue("dateVenue"+i);
            v.setDateSortie("dateSortie"+i);
            v.setTypeVenue("typeVenue"+i);
            v.setUFtraitement("UFtraitement"+i);
            serv.editVenue(v);
        }
        assert(serv.getVenuePatient(pat1).size()==3);
        assert(serv.getVenuePatient(pat2).size()==3);
        
    }
}
