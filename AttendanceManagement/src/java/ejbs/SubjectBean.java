/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dto.AttendantDTO;
import dto.SubjectDTO;
import entities.Attendant;
import entities.Event;
import entities.EventManager;
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
public class SubjectBean {

    @PersistenceContext
    private EntityManager em;

    public void create(int id, String name){
        try {
        
            Subject subject = new Subject(id, name);
            em.persist(subject);
           
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<SubjectDTO> getAll() {
        try {
            List<Subject> subjects = (List<Subject>) em.createNamedQuery("getAllSubjects").getResultList();
            return subjectsToDTOs(subjects);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
       
    public void remove(int id) {
        try {
            Subject subject = em.find(Subject.class, id);
            if (subject == null) {
                throw new EJBException("There is no subject with that code.");
            }
            
            
            for (Attendant attendant : subject.getAttendants()) {
                attendant.removeSubject(subject);
            }
            
            for (EventManager manager : subject.getManagers()) {
                manager.removeSubject(subject);
            }      
            
            for (Event event : subject.getEvents()) {
                event.getSubject().removeEvent(event);
            }   
               
            em.remove(subject);
            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }  
    
    public List<SubjectDTO> getEventManagerSubjects(long id) {
        try {
            EventManager manager = em.find(EventManager.class, id);
            if (manager == null) {
                throw new EJBException("Manager does not exists.");
            }

            return subjectsToDTOs(manager.getSubjects());
            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<SubjectDTO> getAttendantSubjects(long id) {
        try {
            Attendant attendant = em.find(Attendant.class, id);
            if (attendant == null) {
                throw new EJBException("Attendant does not exists.");
            }

            return subjectsToDTOs(attendant.getSubjects());
            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
 
    
    List<SubjectDTO> subjectsToDTOs(List<Subject> subjects) {
        List<SubjectDTO> dtos = new ArrayList<>();
        for(Subject subject : subjects) {
            dtos.add(new SubjectDTO(
                    subject.getId(),
                    subject.getName()));
        }
        return dtos;
    }
}
