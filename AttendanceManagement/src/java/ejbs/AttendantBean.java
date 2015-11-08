/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dto.AttendantDTO;
import entities.Attendant;
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
public class AttendantBean {
    
    @PersistenceContext
    private EntityManager em;

    public void create(long id, String password, String name, String email) {
        try {
            Attendant attendant = new Attendant(id, password, name, email);
            em.persist(attendant);
            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    

    
     public List<AttendantDTO> getAll() {
        try {
            List<Attendant> attendants = (List<Attendant>) em.createNamedQuery("getAllAttendants").getResultList();
            return attendantsToDTOs(attendants);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
        
            AttendantDTO attendantToDTO(Attendant attendant) {
        return new AttendantDTO(
                attendant.getId(),
                null,
                attendant.getName(),
                attendant.getEmail());
    }

    List<AttendantDTO> attendantsToDTOs(List<Attendant> attendants) {
        List<AttendantDTO> dtos = new ArrayList<>();
        for (Attendant a : attendants) {
            dtos.add(attendantToDTO(a));
        }
        return dtos;
    }

}
