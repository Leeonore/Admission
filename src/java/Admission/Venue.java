/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admission;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

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
    
    @Column
    private int IPP;
    private Date dateVenue;
    private Date dateSortie;
    private String UFtraitement;
    private String typeVenue;

    public int getIEP() {
        return IEP;
    }

    public int getIPP() {
        return IPP;
    }

    public Date getDateVenue() {
        return dateVenue;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public String getUFtraitement() {
        return UFtraitement;
    }

    public String getTypeVenue() {
        return typeVenue;
    }

    public void setIEP(int IEP) {
        this.IEP = IEP;
    }

    public void setIPP(int IPP) {
        this.IPP = IPP;
    }

    public void setDateVenue(Date dateVenue) {
        this.dateVenue = dateVenue;
    }

    public void setDateSortie(Date dateSortie) {
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
