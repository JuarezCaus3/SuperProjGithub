/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dto.EventManagerDTO;
import entities.EventManager;
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
public class EventManagerBean {
    
    @PersistenceContext
    private EntityManager em;

    public void create(long id, String password, String name, String email) {
        try {

            em.persist(new EventManager(id, password, name, email));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    

    
     public List<EventManagerDTO> getAll() {
        try {
            List<EventManager> eventManager = (List<EventManager>) em.createNamedQuery("getAllEventManager").getResultList();
            return eventManagersToDTOs(eventManager);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
        
            EventManagerDTO eventManagerToDTO(EventManager eventManager) {
        return new EventManagerDTO(
                eventManager.getId(),
                null,
                eventManager.getName(),
                eventManager.getEmail());
    }

    List<EventManagerDTO> eventManagersToDTOs(List<EventManager> eventManagers) {
        List<EventManagerDTO> dtos = new ArrayList<>();
        for (EventManager e : eventManagers) {
            dtos.add(eventManagerToDTO(e));
        }
        return dtos;
    }

}