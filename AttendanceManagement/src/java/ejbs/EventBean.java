/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dto.EventDTO;
import entities.Attendant;
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

    public void create(int id, String name, String room, Date date, int hora, int week, int subject_code, long manager_code, boolean status) {
        try {
            Subject subject = em.find(Subject.class, subject_code);
            EventManager manager = em.find(EventManager.class, manager_code);
            em.persist(new Event(id, name, room, date, hora, week, subject, manager, status));
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
    
    public List<EventDTO> getAllOpen() {
        try {
            List<Event> events = (List<Event>) em.createNamedQuery("getAllOpenEvents")
                    .setParameter("status", true)
                    .getResultList();
            return eventsToDTOs(events);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public Event getEvent(int id) {
        try {
            Event event = em.find(Event.class, id);
            return event;
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void update(int id, String name, String room, Date date, int hora, int week, int subject_code, long manager_code) {
        try {
            
            Subject subject = em.find(Subject.class, subject_code);
            if (subject == null) {
                throw new EJBException("There is no subject with that code.");
            }
            EventManager manager = em.find(EventManager.class, manager_code);
            if (manager == null) {
                throw new EJBException("There is no manager with that code.");
            }
            Event event = em.find(Event.class, id);
            if (event == null) {
               throw new EJBException("This event id does not exist");
            }
           
            event.setName(name);
            event.setRoom(room);
            event.setDate(date);
            event.setHora(hora);
            event.setWeek(week);
            event.getSubject().removeEvent(event);
            event.setSubject(subject);
            subject.addEvent(event);
            event.getManager().removeEvent(event);
            manager.addEvent(event);
            em.merge(event);
                     
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void remove(long id) {
        try {

            Event event = em.find(Event.class, id);
            if (event == null) {
               throw new EJBException("This event id does not exist");
            }
            
            event.getManager().removeEvent(event);
            event.getSubject().removeEvent(event);
            
            for (Attendant attendant : event.getAttendants()) {
                attendant.removeEvent(event);
            }
            
            em.remove(event);
        
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
    public List<EventDTO> getEventManagerEvents(long id) {
        try {
            EventManager manager = em.find(EventManager.class, id);
            if (manager == null) {
                throw new EJBException("Manager does not exists.");
            }

            return eventsToDTOs(manager.getEvents());
            
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
        
    public List<EventDTO> getAttendantEvents(long id) {
        try {
            Attendant attendant = em.find(Attendant.class, id);
            if (attendant == null) {
                throw new EJBException("Attendant does not exists.");
            }

            return eventsToDTOs(attendant.getEvents());
            
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
                event.getHora(),
                event.getWeek(),
                event.getSubject().getId(),
                event.getManager().getId(),
                event.isStatus());
    }

    List<EventDTO> eventsToDTOs(List<Event> events) {
        List<EventDTO> dtos = new ArrayList<>();
        for (Event e : events) {
            dtos.add(eventToDTO(e));
        }
        return dtos;
    }

}
