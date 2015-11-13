/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dto.EventDTO;
import entities.Event;
import entities.EventManager;
import entities.Subject;
import java.util.ArrayList;
import java.util.Date;
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
public class EventBean {
    
    @PersistenceContext
    private EntityManager em;

    public void create(int id, String name, String room, Date date, int hour, int week, int subject_code, int manager_code) {
        try {
            Subject subject = em.find(Subject.class, subject_code);
            EventManager manager = em.find(EventManager.class, manager_code);
            em.persist(new Event(id, name, room, date, hour, week, subject, manager));
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<EventDTO> getAll() {
        try {
            List<Event> events = (List<Event>) em.createNamedQuery("getAllEvents").getResultList();
            return eventsToDTOs(events);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
        
            EventDTO eventToDTO(Event event) {
        return new EventDTO(
                event.getId(),
                event.getName(),
                event.getRoom(),
                event.getDate(),
                event.getHour(),
                event.getWeek());
    }

    List<EventDTO> eventsToDTOs(List<Event> events) {
        List<EventDTO> dtos = new ArrayList<>();
        for (Event e : events) {
            dtos.add(eventToDTO(e));
        }
        return dtos;
    }

}
