/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Admission.DatabaseUtils;
import Admission.Patient;
import Admission.Services;
import Admission.Venue;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdmissionTest {

    static EntityManagerFactory fact;

    public AdmissionTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        //fact = Persistence.createEntityManagerFactory("bureauPU");
    }

    @AfterClass
    public static void tearDownClass() {
        //fact.close();
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

//    @Test
//    public void venue(){
//        clean();
//        Services serv = new Services(DatabaseUtils.factTest());
//        Venue ven = serv.newVenue(11111111, 111, "dateVenue1", "dateSortie1", "UFtraitement1", "typeVenue1");
//        assertNotNull(ven); 
//        ven = serv.newVenue(22222222, 222, "dateVenue2", "dateSortie2", "UFtraitement2", "typeVenue2");
//        assertNotNull(ven);
//        ven = serv.newVenue(33333333, 333, "dateVenue3", "dateSortie3", "UFtraitement3", "typeVenue3");
//        assertNotNull(ven);
//        List<Venue> res = (List<Venue>) serv.getVenueByIEP(333);
//        assert(!res.isEmpty());
//        assert(res.size() == 1);
//      
//        res = serv.getAllVenue();
//        assert(!res.isEmpty());
//        assert(res.size() == 3);
//    }
    @Test
    public void AddOnePatient() {
        //clean();
    //Test de la création d'un patient
        Services serv = new Services(DatabaseUtils.factTest());
        Patient pat = new Patient();
        pat.setNom("Nom1");
        pat.setAdresse("Adresse1");
        pat.setDateNaiss("DateNaiss1");
        pat.setPrenom("prenom1");
        pat.setNumSecu("numsecu1");
        pat.setPhone("telephone1");
        pat.setSexe("sexe1");
        serv.newPatient(pat);
        // Un patient a bien été créé ?
        assert(!serv.getAllPatient().isEmpty());
        assert(serv.getAllPatient().size()== 1);
        
        // Le patient contient les (bonnes) info ?
        Patient patient = serv.getAllPatient().get(0);
        assert (patient != null );        
        assert (patient.getNom().equals("Nom1") );
        assert (patient.getDateNaiss().equals("DateNaiss1") );
        assert (patient.getPrenom().equals("prenom1") );
        assert (patient.getPhone().equals("telephone1") );
        assert (patient.getAdresse().equals("Adresse1") );
        assert (patient.getNumSecu().equals("numsecu1") );
        assert (patient.getSexe().equals("sexe1") );
 
        //Les fonctions de recherche fonctionnent-elles ?
        assert (serv.getPatientByIPP(patient.getIPP()) != null); //Vérifié : bien valeur "null" si existe pas
        assert (!serv.FindPatient("Nom1", "prenom1", "DateNaiss1").isEmpty());
        System.out.println("testFind" + serv.FindPatient("Nom1", "prenom1", "DateNaiss1"));
    }
    
        @Test
    public void RemoveAllPatient() {
        //clean();
    //Créer deux patients
        Services serv = new Services(DatabaseUtils.factTest());
        for (int i=1; i<3; i++){
            Patient pat = new Patient();
            pat.setNom("Nom1");
            pat.setAdresse("Adresse" + i);
            pat.setDateNaiss("DateNaiss1");
            pat.setPrenom("prenom1");
            pat.setNumSecu("numsecu" + i);
            pat.setPhone("telephone" + i);
            pat.setSexe("sexe" + i);
            serv.newPatient(pat);
         }
        assert(!serv.getAllPatient().isEmpty());
        
    // Supprimer les patients
        serv.removeAllPatient();
    
    // Tester la suppression
        assert (serv.getAllPatient().isEmpty());
    }
    
    @Test
    public void AddMultiPatient() {
        //clean();
    //Test de la création de plusieurs patients
        Services serv = new Services(DatabaseUtils.factTest());
        List<Patient> liste = new ArrayList<>();
        for (int i=1; i<4; i++){
            Patient pat = new Patient();
            pat.setNom("Nom" + i);
            pat.setAdresse("Adresse" + i);
            pat.setDateNaiss("DateNaiss" + i);
            pat.setPrenom("prenom" + i);
            pat.setNumSecu("numsecu" + i);
            pat.setPhone("telephone" + i);
            pat.setSexe("sexe" + i);
            liste.add(pat);
            serv.newPatient(pat);
        }
        // Les patients ont bien été créés ?
        assert(!serv.getAllPatient().isEmpty());
        assert(serv.getAllPatient().size()==3);
        
        // Les patient contiennent les (bonnes) info ?
        // Le patient 1 :
        Patient patient = serv.getAllPatient().get(0);
        assert (patient != null );        
        assert (patient.getNom().equals("Nom1") );
        assert (patient.getDateNaiss().equals("DateNaiss1") );
        assert (patient.getPrenom().equals("prenom1") );
        assert (patient.getPhone().equals("telephone1") );
        assert (patient.getAdresse().equals("Adresse1") );
        assert (patient.getNumSecu().equals("numsecu1") );
        assert (patient.getSexe().equals("sexe1") );
        
        // Le patient 2 :
        patient = serv.getAllPatient().get(1);
        assert (patient != null );        
        assert (patient.getNom().equals("Nom2") );
        assert (patient.getDateNaiss().equals("DateNaiss2") );
        assert (patient.getPrenom().equals("prenom2") );
        assert (patient.getPhone().equals("telephone2") );
        assert (patient.getAdresse().equals("Adresse2") );
        assert (patient.getNumSecu().equals("numsecu2") );
        assert (patient.getSexe().equals("sexe2") );
 
        // Le patient 2 :
        patient = serv.getAllPatient().get(2);
        assert (patient != null );        
        assert (patient.getNom().equals("Nom3") );
        assert (patient.getDateNaiss().equals("DateNaiss3") );
        assert (patient.getPrenom().equals("prenom3") );
        assert (patient.getPhone().equals("telephone3") );
        assert (patient.getAdresse().equals("Adresse3") );
        assert (patient.getNumSecu().equals("numsecu3") );
        assert (patient.getSexe().equals("sexe3") );
        
        //Les fonctions de recherche fonctionnent-elles ?
        assert (serv.getPatientByIPP(liste.get(0).getIPP()) != null);
        assert (serv.getPatientByIPP(liste.get(1).getIPP()) != null);
        assert (serv.getPatientByIPP(liste.get(2).getIPP()) != null);
        
//        assert (!serv.FindPatient("Nom1", "prenom1", "DateNaiss1").isEmpty());
//        assert (serv.FindPatient("Nom1", "prenom1", "DateNaiss1").size() == 1);
//        assert (!serv.FindPatient("Nom2", "prenom2", "DateNaiss2").isEmpty());
//        assert (serv.FindPatient("Nom2", "prenom2", "DateNaiss2").size() == 1);
//        assert (!serv.FindPatient("Nom3", "prenom3", "DateNaiss3").isEmpty());
//        assert (serv.FindPatient("Nom3", "prenom3", "DateNaiss3").size() == 1);
    }
    
    @Test
    public void AddHomoPatient() {
        //clean();
    //Test de la création d'homonyme
        Services serv = new Services(DatabaseUtils.factTest());
        for (int i=1; i<3; i++){
            Patient pat = new Patient();
            pat.setNom("Nom1");
            pat.setAdresse("Adresse" + i);
            pat.setDateNaiss("DateNaiss1");
            pat.setPrenom("prenom1");
            pat.setNumSecu("numsecu" + i);
            pat.setPhone("telephone" + i);
            pat.setSexe("sexe" + i);
            serv.newPatient(pat);
         }

        // Les patients ont bien été créés ?
        assert(!serv.getAllPatient().isEmpty());
        assert(serv.getAllPatient().size()==2);
        
        // Le patient contient les (bonnes) info ?
        Patient patient = serv.getAllPatient().get(0);
        assert (patient != null );        
        assert (patient.getNom().equals("Nom1") );
        assert (patient.getDateNaiss().equals("DateNaiss1") );
        assert (patient.getPrenom().equals("prenom1") );
        assert (patient.getPhone().equals("telephone1") );
        assert (patient.getAdresse().equals("Adresse1") );
        assert (patient.getNumSecu().equals("numsecu1") );
        assert (patient.getSexe().equals("sexe1") );
        
        patient = serv.getAllPatient().get(1);
        assert (patient != null );        
        assert (patient.getNom().equals("Nom1") );
        assert (patient.getDateNaiss().equals("DateNaiss1") );
        assert (patient.getPrenom().equals("prenom1") );
        assert (patient.getPhone().equals("telephone2") );
        assert (patient.getAdresse().equals("Adresse2") );
        assert (patient.getNumSecu().equals("numsecu2") );
        assert (patient.getSexe().equals("sexe2") );
 
        //La fonction de recherche fonctionne-t-elle ?
//        assert (!serv.FindPatient("Nom1", "prenom1", "DateNaiss1").isEmpty());
//        assert (serv.FindPatient("Nom1", "prenom1", "DateNaiss1").size() == 2);
    }
}




