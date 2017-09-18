/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admission;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

/**
 *
 * @author lgrandgu
 */
public class Services {
    EntityManagerFactory fact;
    EntityManager em;
    
    public Services(EntityManagerFactory fact) {
        this.fact = fact;
        this.em = fact.createEntityManager();
    }
    
     public void close() {
        em.close();
    }
    public void newPatient(Patient pat){
        //Créer l'objet patient dans la base
        em.getTransaction( ).begin( );
        em.persist(pat);
        em.getTransaction().commit();
    }
    
    public Patient newPatient(String IPP, String nom, String prenom, Date dateNaiss, String adresse, String phone, String numSS){
        //Créer un nouveau patient lors de sa première venue (IPP, nom, prénom, DateNaiss, adresse, téléphone, n°SS)
        Patient pat= newPatient();
        pat.setIPP(IPP);
        pat.setNom(nom);
        pat.setPrenom(prenom);
        pat.setDateNaiss(dateNaiss);
        pat.setAdresse(adresse);
        pat.setPhone(phone);
        pat.setSS(numSS);
        
        em.getTransaction( ).begin( );
        em.persist(pat);
        em.getTransaction().commit();
    }
    
    public void removePat(String IPP) {
        Patient pat = em.findPat( Patient.class, IPP ); //ça va marcher ???
	em.getTransaction( ).begin( );
        em.remove(pat);
        em.getTransaction().commit();
    }
    public void editPatient(Patient pat){
        //Modifier une patient
        em.getTransaction( ).begin( );
        em.merge(cr);
        em.getTransaction().commit();
    }
    
    public void FindPatient(){
        // Rechercher un patient par nom, prénom, date de naiss
    }
    
    public List<Patient> getAllPatient() {
	TypedQuery<Patient> query = em.createQuery("SELECT p FROM Patient p", Patient.class);
        List<Patient> res = query.getResultList();
        return res;
    }
    
    
    public void newVenue(){
        //Créer une nouvelle venue pour un patient existant (IPP, IEP, DateHeure venue/sortie, UF traitement, type venue)
    }
    
    public void FindVenue(){
        // Rechercher une venue
    }
           
    public void editVenue(){
        //Modifier une venue (dont ajoue date fin)
    }
    
    public List<Venue> getAllVenue() {
	TypedQuery<Venue> query = em.createQuery("SELECT v FROM Venue v", Venue.class);
        List<Venue> res = query.getResultList();      
        return res;
    }
}
