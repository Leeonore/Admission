/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

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

public class PatientTest {

    static EntityManagerFactory fact;

    public PatientTest() {

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
    
//////////////// TEST DES FONCTIONS PATIENTS /////////////////////////////////////////////////
    
    @Test //Tester de la création d'un patient  + recherches
    public void addOnePatient() {
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
        Patient pat = new Patient();
        pat.setNom("nom1");
        pat.setAdresse("adresse1");
        pat.setDateNaiss("dateNaiss1");
        pat.setPrenom("prenom1");
        pat.setNumSecu("numsecu1");
        pat.setPhone("telephone1");
        pat.setSexe("sexe1");
        serv.editPatient(pat);
        // Un patient a bien été créé ?
        assert (!serv.getAllPatient().isEmpty());
        assert (serv.getAllPatient().size() == 1);

        // Le patient contient les (bonnes) info ?
        Patient patient = serv.getAllPatient().get(0);
        assert (patient != null);
        assert (patient.getNom().equals("nom1"));
        assert (patient.getDateNaiss().equals("dateNaiss1"));
        assert (patient.getPrenom().equals("prenom1"));
        assert (patient.getPhone().equals("telephone1"));
        assert (patient.getAdresse().equals("adresse1"));
        assert (patient.getNumSecu().equals("numsecu1"));
        assert (patient.getSexe().equals("sexe1"));

        //Les fonctions de recherche fonctionnent-elles ?
        assert (serv.getPatientByIPP(patient.getIPP()) != null); //Vérifié : bien valeur "null" si existe pas
        assert (!serv.findPatient("nom1", "prenom1", "dateNaiss1").isEmpty());
    }

    @Test // Tester la suppression de tous les patients
    public void removeAllPatient() {
        clean();
        //Créer deux patients
        Services serv = new Services(DatabaseUtils.factTest());
        for (int i = 1; i < 3; i++) {
            Patient pat = new Patient();
            pat.setNom("nom" + i);
            pat.setAdresse("adresse" + i);
            pat.setDateNaiss("dateNaiss" + i);
            pat.setPrenom("prenom" + i);
            pat.setNumSecu("numsecu" + i);
            pat.setPhone("telephone" + i);
            pat.setSexe("sexe" + i);
            serv.editPatient(pat);
        }
        assert (!serv.getAllPatient().isEmpty());

        // Supprimer les patients
        serv.removeAllPatient();

        // Tester la suppression
        assert (serv.getAllPatient().isEmpty());
    }

    @Test // Tester la suppression d'un patient
    public void removeOnePatient() {
        clean();
        //Créer deux patients
        Services serv = new Services(DatabaseUtils.factTest());
        for (int i = 1; i < 3; i++) {
            Patient pat = new Patient();
            pat.setNom("nom" + i);
            pat.setAdresse("adresse" + i);
            pat.setDateNaiss("dateNaiss" + i);
            pat.setPrenom("prenom" + i);
            pat.setNumSecu("numsecu" + i);
            pat.setPhone("telephone" + i);
            pat.setSexe("sexe" + i);
            serv.editPatient(pat);
        }
        assert (!serv.getAllPatient().isEmpty()); //Ils sont bien créés ?

        //Suppression du patient 2
        Patient patient = serv.getAllPatient().get(1);
        serv.removePatient(patient.getIPP());
        assert (serv.getAllPatient().size() == 1); //Un patient a bien été supprimé

        //Le bon patient a été supprimé ?
        assert (!serv.findPatient("nom1", "prenom1", "dateNaiss1").isEmpty()); // Le patient 1 existe
        assert (serv.findPatient("nom2", "prenom2", "dateNaiss2").isEmpty()); // Le patient 2 n'existe pas

    }

    @Test //Tester la création de plusieurs patients + recherches
    public void addMultiPatient() {
        clean();
        //Test de la création de plusieurs patients
        Services serv = new Services(DatabaseUtils.factTest());
        List<Patient> liste = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            Patient pat = new Patient();
            pat.setNom("nom" + i);
            pat.setAdresse("adresse" + i);
            pat.setDateNaiss("dateNaiss" + i);
            pat.setPrenom("prenom" + i);
            pat.setNumSecu("numsecu" + i);
            pat.setPhone("telephone" + i);
            pat.setSexe("sexe" + i);
            serv.editPatient(pat);
            liste.add(pat);
        }
        // Les patients ont bien été créés ?
        assert (!serv.getAllPatient().isEmpty());
        assert (serv.getAllPatient().size() == 3);

        // Les patient contiennent les (bonnes) info ?
        for (int i = 0; i < 3; i++) {
            Patient patient = serv.getAllPatient().get(i);
            assert (patient != null);
            int j = i + 1;
            assert (patient.getNom().equals("nom" + j));
            assert (patient.getDateNaiss().equals("dateNaiss" + j));
            assert (patient.getPrenom().equals("prenom" + j));
            assert (patient.getPhone().equals("telephone" + j));
            assert (patient.getAdresse().equals("adresse" + j));
            assert (patient.getNumSecu().equals("numsecu" + j));
            assert (patient.getSexe().equals("sexe" + j));
        }

        //Les fonctions de recherche fonctionnent-elles ?
        assert (serv.getPatientByIPP(liste.get(0).getIPP()) != null);
        assert (serv.getPatientByIPP(liste.get(1).getIPP()) != null);
        assert (serv.getPatientByIPP(liste.get(2).getIPP()) != null);

        assert (!serv.findPatient("nom1", "prenom1", "dateNaiss1").isEmpty());
        assert (serv.findPatient("nom1", "prenom1", "dateNaiss1").size() == 1);
        assert (!serv.findPatient("nom2", "prenom2", "dateNaiss2").isEmpty());
        assert (serv.findPatient("nom2", "prenom2", "dateNaiss2").size() == 1);
        assert (!serv.findPatient("nom3", "prenom3", "dateNaiss3").isEmpty());
        assert (serv.findPatient("nom3", "prenom3", "dateNaiss3").size() == 1);
    }

    @Test //Tester la création de deux homonymes + recherches
    public void addHomoPatient() {
        clean();
        //Test de la création d'homonyme
        Services serv = new Services(DatabaseUtils.factTest());
        for (int i = 1; i < 3; i++) {
            Patient pat = new Patient();
            pat.setNom("nom1");
            pat.setAdresse("adresse1");
            pat.setDateNaiss("dateNaiss1");
            pat.setPrenom("prenom" + i);
            pat.setNumSecu("numsecu" + i);
            pat.setPhone("telephone" + i);
            pat.setSexe("sexe" + i);
            serv.editPatient(pat);
        }

        // Les patients ont bien été créés ?
        assert (!serv.getAllPatient().isEmpty());
        assert (serv.getAllPatient().size() == 2);

        // Le patient contient les (bonnes) info ?
        for (int i = 0; i < 2; i++) {
            Patient patient = serv.getAllPatient().get(i);
            assert (patient != null);
            int j = i + 1;
            assert (patient.getNom().equals("nom1"));
            assert (patient.getDateNaiss().equals("dateNaiss1"));
            assert (patient.getPrenom().equals("prenom1"));
            assert (patient.getPhone().equals("telephone" + j));
            assert (patient.getAdresse().equals("adresse" + j));
            assert (patient.getNumSecu().equals("numsecu" + j));
            assert (patient.getSexe().equals("sexe" + j));
        }

        //La fonction de recherche fonctionne-t-elle ?
        assert (!serv.findPatient("nom1", "prenom1", "dateNaiss1").isEmpty());
        assert (serv.findPatient("nom1", "prenom1", "dateNaiss1").size() == 2);
    }

    @Test //Tester la modification d'un patient
    public void editPatient() {
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
        Patient pat = new Patient();
        pat.setNom("nom1");
        pat.setAdresse("adresse1");
        pat.setDateNaiss("dateNaiss1");
        pat.setPrenom("prenom1");
        pat.setNumSecu("numsecu1");
        pat.setPhone("telephone1");
        pat.setSexe("sexe1");
        serv.editPatient(pat);
        Patient patient = serv.getAllPatient().get(0);
        patient.setAdresse("adresse2");
        serv.editPatient(patient);

        //Test de la modif
        assert (serv.getAllPatient().get(0).getAdresse().equals("adresse2"));
        System.out.println("test231" + serv.getAllPatient().get(0).getAdresse());
    }
    
    @Test //Tester la recherche de doublon
    public void findDoublonTest(){
        clean();
        Services serv = new Services(DatabaseUtils.factTest());
        Patient pat = new Patient();
        pat.setNom("nom1");
        pat.setAdresse("adresse1");
        pat.setDateNaiss("dateNaiss1");
        pat.setPrenom("prenom1");
        pat.setNumSecu("numsecu1");
        pat.setPhone("telephone1");
        pat.setSexe("sexe1");
        serv.editPatient(pat);
        
        Patient pat1 = new Patient();
            pat1.setNom("bernard");
            pat1.setAdresse("prenom2");
            pat1.setDateNaiss("dateNaiss2");
        Patient pat2 = new Patient();
            pat1.setNom("nom1");
            pat1.setAdresse("prenom1");
            pat1.setDateNaiss("dateNaiss1");    
        assert(!serv.findDoublon(pat1));
        assert(serv.findDoublon(pat2));
    }
    
}
