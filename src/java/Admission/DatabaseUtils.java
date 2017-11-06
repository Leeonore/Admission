/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admission;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DatabaseUtils {
    
    private static EntityManagerFactory fact = null;
    private static EntityManager em = null;


    static public EntityManager fact() {
        if (fact == null) {
             fact = Persistence.createEntityManagerFactory("AdmissionPU");
             em = fact.createEntityManager();
        }
        return em;
   }
    
   static public void close() {
       if (em != null)  em.close();
       if (fact != null) fact.close();
   }
   
    static public EntityManager factTest() {
         if (fact == null) {
             fact = Persistence.createEntityManagerFactory("AdmissionTestPU");
             em = fact.createEntityManager();
         }
         return em; 
   }
    
    
}