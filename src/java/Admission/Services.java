/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admission;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author lgrandgu
 */
public class Services {
    EntityManager em;
    
    public Services(EntityManager em) {
        this.em = em;
    }
    
/** Gestion des patients **/
    public void newPatient(Patient pat){
        //Créer l'objet patient dans la base
        em.getTransaction( ).begin( );
        em.persist(pat);
        em.getTransaction().commit();
    }
    
    public Patient newPatient(int IPP, String nom, String prenom, String dateNaiss, String adresse, String phone, String numSS){
        //Créer un nouveau patient lors de sa première venue (IPP, nom, prénom, DateNaiss, adresse, téléphone, n°SS)
        Patient pat= new Patient();
        pat.setIPP(IPP);
        pat.setNom(nom);
        pat.setPrenom(prenom);
        pat.setDateNaiss(dateNaiss);
        pat.setAdresse(adresse);
        pat.setPhone(phone);
        pat.setNumSecu(numSS);
        
        em.getTransaction( ).begin( );
        em.persist(pat);
        em.getTransaction().commit();
        
        return pat;
    }
    
    public void removePatient(int IPP) { //utile ? En théorie on ne supprime jamais un patient.
        Patient pat = em.find( Patient.class, IPP ); //ça va marcher ???
	em.getTransaction( ).begin( );
        em.remove(pat);
        em.getTransaction().commit();
    }
    
    public void removeAllPatient() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Patient").executeUpdate();
        em.getTransaction().commit();
    }
    
    public void editPatient(Patient pat){
        //Modifier une patient
        em.getTransaction( ).begin( );
        em.merge(pat);
        em.getTransaction().commit();
    }
    
    public Patient getPatientByIPP(int IPP) {       
	Patient res = em.find( Patient.class, IPP );      
        return res;
    }
    
    public void FindPatient(String nom, String prenom, String dateNaiss ){
        // Rechercher un patient par nom, prénom, date de naiss
        TypedQuery<Patient> query = em.createQuery("SELECT p FROM Patient p WHERE p.Nom = :nom AND p.prenom=:prenom AND p.dateNaiss = :dateNaiss", Patient.class); //MODIFIER
        List<Patient> res = query.getResultList();
    }
    
    public List<Patient> getAllPatient() {
	TypedQuery<Patient> query = em.createQuery("SELECT p FROM Patient p", Patient.class);
        List<Patient> res = query.getResultList();
        return res;
    }
    
/** Gestion des Venues **/    
    public void newVenue(Venue venue){
        //Créer une nouvelle venue pour un patient existant (IPP, IEP, DateHeure venue/sortie, UF traitement, type venue)
        em.getTransaction( ).begin( );
        em.persist(venue);
        em.getTransaction().commit();
    }
    
    public Venue newVenue(int IPP, int IEP, String dateVenue, String dateSortie, String UFtraitement, String typeVenue){
        //Créer une nouvelle venue pour un patient existant (IPP, IEP, DateHeure venue/sortie, UF traitement, type venue)
        Venue venue= new Venue();
        venue.setIPP(IPP);
        venue.setIEP(IEP);
        venue.setDateVenue(dateVenue);
        venue.setDateSortie(dateSortie);
        venue.setUFtraitement(UFtraitement);
        venue.setTypeVenue(typeVenue);
        
        em.getTransaction( ).begin( );
        em.persist(venue);
        em.getTransaction().commit();
        
        return venue;
    }
        
    public void FindVenue(int IPP, int IEP, String dateVenue){
        // Rechercher une venue
        TypedQuery<Venue> query = em.createQuery("SELECT v FROM Venue v WHERE v.IPP = :IPP AND v.IEP=:IEP AND v.dateVenue = :dateVenue", Venue.class);
        List<Venue> res = query.getResultList();
    }

    public Venue getVenueByIEP(int IEP) {       
	Venue res = em.find( Venue.class, IEP );      
        return res;
    }
    
        public void removeVenue(int IEP) { //utile ? En théorie on ne supprime jamais un patient.
        Venue ve = em.find( Venue.class, IEP ); //ça va marcher ???
	em.getTransaction( ).begin( );
        em.remove(ve);
        em.getTransaction().commit();
    }
    
    public void removeAllVenue() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Venue").executeUpdate();
        em.getTransaction().commit();
    }
    
    public void editVenue(Venue venue){
        //Modifier une venue (dont ajoue date fin)
        em.getTransaction( ).begin( );
        em.merge(venue);
        em.getTransaction().commit();
    }
    
    public List<Venue> getAllVenue() {
	TypedQuery<Venue> query = em.createQuery("SELECT v FROM Venue v", Venue.class);
        List<Venue> res = query.getResultList();      
        return res;
    }
}
