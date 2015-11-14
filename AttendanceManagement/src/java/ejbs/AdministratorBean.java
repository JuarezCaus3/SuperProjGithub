/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dto.AdministratorDTO;
import entities.Administrator;
import java.util.ArrayList;
import java.util.List;
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

    public void create(long id, String password, String name, String email) {
        try {
            if(em.find(Administrator.class, id) != null){
                return;
            }
            em.persist(new Administrator(id, password, name, email));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public AdministratorDTO getAdministrator(long id) {
        try {
            Administrator admin = em.find(Administrator.class, id);      
            return administratorToDTO(admin);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void update(long id, String password, String name, String email) {
        try {
            
            Administrator admin = em.find(Administrator.class, id);
            if (admin == null) {
               throw new EJBException("This administrator id does not exist");
            }
           
            admin.setPassword(password);
            admin.setName(name);
            admin.setEmail(email);
            em.merge(admin);
                     
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void remove(long id) {
        try {

            Administrator admin = em.find(Administrator.class, id);
            if (admin == null) {
               throw new EJBException("This administrator id does not exist");
            }
                                
            em.remove(admin);
        
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
     public List<AdministratorDTO> getAll() {
        try {
            List<Administrator> admin = (List<Administrator>) em.createNamedQuery("getAllAdministrators").getResultList();
            return administratorsToDTOs(admin);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
        
            AdministratorDTO administratorToDTO(Administrator admin) {
        return new AdministratorDTO(
                admin.getId(),
                null,
                admin.getName(),
                admin.getEmail());
    }

    List<AdministratorDTO> administratorsToDTOs(List<Administrator> administrators) {
        List<AdministratorDTO> dtos = new ArrayList<>();
        for (Administrator a : administrators) {
            dtos.add(administratorToDTO(a));
        }
        return dtos;
    }
}
