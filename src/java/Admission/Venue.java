/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admission;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nrodrigu
 */

@Entity
@XmlRootElement
public class Venue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IEP;
    
    @ManyToOne
    private Patient patient;
    @Column
    private String dateVenue;
    @Column
    private String dateSortie;
    @Column
    private String UFtraitement;
    @Column
    private String typeVenue;

    public int getIEP() {
        return IEP;
    }
    

    public void setIEP(int IEP) {
        this.IEP = IEP;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDateVenue() {
        return dateVenue;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public String getUFtraitement() {
        return UFtraitement;
    }

    public String getTypeVenue() {
        return typeVenue;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDateVenue(String dateVenue) {
        this.dateVenue = dateVenue;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public void setUFtraitement(String UFtraitement) {
        this.UFtraitement = UFtraitement;
    }

    public void setTypeVenue(String typeVenue) {
        this.typeVenue = typeVenue;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) IEP;
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venue)) {
            return false;
        }
        Venue other = (Venue) object;
        if (this.IEP != other.IEP) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "admission.Venue[ iep=" + IEP + " ]";
    }
}
