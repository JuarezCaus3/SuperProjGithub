/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dto.AttendantDTO;
import entities.Attendant;
import entities.Event;
import entities.Subject;
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
            System.out.println(attendant.toString());
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
     
    public AttendantDTO getAttendant(long id) {
        try {
            Attendant attendant = em.find(Attendant.class, id);
            
            return attendantToDTO(attendant);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void update(long id, String password, String name, String email) {
        try {

            Attendant attendant = em.find(Attendant.class, id);
            if (attendant == null) {
               throw new EJBException("This attendant id does not exist");
            }

            attendant.setPassword(password);
            attendant.setName(name);
            attendant.setEmail(email);
            em.merge(attendant);
                     
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void remove(long id) {
        try {
            Attendant attendant = em.find(Attendant.class, id);
            if (attendant == null) {
               throw new EJBException("This attendant id does not exist");
            }
            
            for (Subject subject : attendant.getSubjects()) {
                subject.removeAttendant(attendant);
            }
            
            for (Event event : attendant.getEvents()) {
                event.removeAttendant(attendant);
            }
            
            em.remove(attendant);
        
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    /////////// Students And Subjects \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public void enrollAttendant(long id, int subject_code) {
        try {

            Attendant attendant = em.find(Attendant.class, id);
            if (attendant == null) {
               throw new EJBException("This attendant id does not exist");
            }

            Subject subject = em.find(Subject.class, subject_code);
            if (subject == null) {
                throw new EJBException("This subject id does not exist");
            }

            if (subject.getAttendants().contains(attendant)) {
                throw new EJBException("This attendant is already enrolled on this subject");
            }

            subject.addAttendant(attendant);
            attendant.addSubject(subject);

        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void unrollAttendant(long id, int subject_code) {
        try {
            Attendant attendant = em.find(Attendant.class, id);
            if (attendant == null) {
               throw new EJBException("This attendant id does not exist");
            }

            Subject subject = em.find(Subject.class, subject_code);
            if (subject == null) {
                throw new EJBException("This subject id does not exist");
            }
            
             if (!subject.getAttendants().contains(attendant)) {
                throw new EJBException("This attendant is not enrolled on this subject");
            }            
            
            subject.removeAttendant(attendant);
            attendant.removeSubject(subject);
           
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<AttendantDTO> getEnrolledAttendants(int subject_code) {
        try {
            Subject subject = em.find(Subject.class, subject_code);
            if( subject == null){
                throw new EJBException("This subject id does not exist");
            }            
            List<Attendant> attendants = (List<Attendant>) subject.getAttendants();
            return attendantsToDTOs(attendants);
           
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<AttendantDTO> getUnrolledAttendants(int subject_code) {
        try {
            Subject subject = em.find(Subject.class, subject_code);
            if( subject == null){
               throw new EJBException("This subject id does not exist");
            }            
            List<Attendant> attendants= (List<Attendant>) em.createNamedQuery("getAllAttendants").getResultList();
            List<Attendant> enrolled = em.find(Subject.class, subject_code).getAttendants();
            attendants.removeAll(enrolled);
            return attendantsToDTOs(attendants);           
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    
    /////////// Students And Events \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    
    
    public void checkInAttendant(long id, int event_code) {
        try {

            Attendant attendant = em.find(Attendant.class, id);
            if (attendant == null) {
               throw new EJBException("This attendant id does not exist");
            }

            Event event = em.find(Event.class, event_code);
            if (event == null) {
                throw new EJBException("This event id does not exist");
            }

            if (event.getAttendants().contains(attendant)) {
                throw new EJBException("This attendant is already enrolled on this subject");
            }

            event.addAttendant(attendant);
            attendant.addEvent(event);

        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public void checkOutAttendant(long id, int event_code) {
        try {
            Attendant attendant = em.find(Attendant.class, id);
            if (attendant == null) {
               throw new EJBException("This attendant id does not exist");
            }

            Event event = em.find(Event.class, event_code);
            if (event == null) {
                throw new EJBException("This event id does not exist");
            }
            
             if (!event.getAttendants().contains(attendant)) {
                throw new EJBException("This attendant is not enrolled on this subject");
            }            
            
            event.removeAttendant(attendant);
            attendant.removeEvent(event);
           
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<AttendantDTO> getCheckedInAttendants(int event_code) {
        try {
            Event event = em.find(Event.class, event_code);
            if( event == null){
                throw new EJBException("This event id does not exist");
            }            
            List<Attendant> attendants = (List<Attendant>) event.getAttendants();
            return attendantsToDTOs(attendants);
           
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<AttendantDTO> getCheckedOutAttendants(int event_code) {
        try {
            Event event = em.find(Event.class, event_code);
            if( event == null){
               throw new EJBException("This event id does not exist");
            }            
            List<Attendant> attendants= (List<Attendant>) em.createNamedQuery("getAllAttendants").getResultList();
            List<Attendant> enrolled = em.find(Event.class, event_code).getAttendants();
            attendants.removeAll(enrolled);
            return attendantsToDTOs(attendants);           
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    ///////////// Attendant DTO \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
           
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
