/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admission;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author nrodrigu
 */

@Path("generic")
public class RestServices {
    Services serv;
    
    public RestServices(){
        serv = new Services(DatabaseUtils.fact());
    }
/* -------------------- RestService sur les patients  */  
    //Liste de tous les patients
    
    @GET
    @Path("patients")
    @Produces("application/json")
    public List<Patient> getAllPatients(){
        return serv.getAllPatient();
    }
    /*
    //Recherche de patient par nom, prenom, date de naissance
    @GET
    @Path("patients")
    @Produces("application/json")
    public List<Patient> findPatient(@DefaultValue("") @QueryParam("nom") String nom,@DefaultValue("") @QueryParam("prenom") String prenom,@DefaultValue("") @QueryParam("datenaiss") String dateNaiss){
        return serv.findPatient(nom, prenom, dateNaiss);
    }*/

    
    //Recherche de patient par IPP
    @GET
    @Path("patiens/{ipp}")
    @Produces("application/json")
    public Patient getPatient(@PathParam("ipp") int IPP) {
        return serv.getPatientByIPP(IPP);
    }
    
    //Creation d'un patient
    @POST
    @Path("patients")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Patient newPatient(Patient pat){
        serv.newPatient(pat);
        return pat;
    }
    
    //Modification d'un patient
    @POST
    @Path("patients/{ipp}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editPatient(Patient pat){
        serv.editPatient(pat);
        return Response.status(200).entity(pat).build();
    }
    
    //Suppression de tous les patients
    @DELETE
    @Path("/patients")
    public Response removeAllPatient(){
        serv.removeAllPatient();
        return Response.status(200).build();
    }
    
    //Suppression d'un patient
    @DELETE
    @Path("/patients/{ipp}")
    public Response removePatient(@PathParam("ipp") int IPP){
        serv.removePatient(IPP);
        return Response.status(200).build();
    }
    
    
/* -------------------- RestService sur les venues  */  
    //Liste de toutes les venues
    @GET
    @Path("venues")
    @Produces("application/json")
    public List<Venue> getAllVenues(){
        return serv.getAllVenue();
    }
        
    //Recherche de venue par IPP, IEP, date de venue
    @GET
    @Path("patiens?ipp=<IPP>&iep=<IEP>&datevenue=<dateVenue>")
    @Produces("application/json")
    public List<Venue> findVenue(@DefaultValue("") @QueryParam("ipp") int IPP,@DefaultValue("") @QueryParam("iep") int IEP,@DefaultValue("") @QueryParam("datevenue") String dateVenue){
        return serv.findVenue(IPP, IEP, dateVenue);
    }
    
    //Recherche de venue par IEP
    @GET
    @Path("venues/{iep}")
    @Produces("application/json")
    public Venue getVenue(@PathParam("iep") int IEP) {
        return serv.getVenueByIEP(IEP);
    }
    
    //Creation d'une venue
    @POST
    @Path("venues")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    public Venue newVenue(Venue ven){
        serv.newVenue(ven);
        return ven;
    }
    
    //Modification d'un patient
    @POST
    @Path("venues/{ipp}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editVenue(Venue ven){
        serv.editVenue(ven);
        return Response.status(200).entity(ven).build();
    }
    
    //Suppression de tous les patients
    @DELETE
    @Path("/venues")
    public Response removeAllVenues(){
        serv.removeAllVenue();
        return Response.status(200).build();
    }
    
    //Suppression d'un patient
    @DELETE
    @Path("/venues/{iep}")
    public Response removeVenue(@PathParam("iep") int IEP){
        serv.removeVenue(IEP);
        return Response.status(200).build();
    }
}
