/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.Administrator;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author David
 */
@Stateless
public class AdministratorBean {

    @PersistenceContext
    private EntityManager em;

    public void create(int id, String password, String name, String email) {
        try {
            if(em.find(Administrator.class, id) != null){
                return;
            }
            em.persist(new Administrator(id, password, name, email));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
}
