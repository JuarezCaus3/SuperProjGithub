/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Kazhw
 */
@Entity
@Table(name = "SUBJECTS",
        uniqueConstraints
        = @UniqueConstraint(columnNames = {"NAME"}))
@NamedQuery(name = "getAllSubjects",
        query = "SELECT s FROM Subject s ORDER BY s.name")
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private int id;
    @NotNull
    private String name;
    
    
    @ManyToMany(mappedBy = "subjects")
    private  List<EventManager> managers;
    
    @ManyToMany (mappedBy ="subjects")
    private List<Attendant> attendants;
        
    @OneToMany(mappedBy = "subject")
    private List<Event> events;

    public Subject() {
        attendants = new LinkedList<>();
        managers = new LinkedList<>();
        events = new LinkedList<>();
    }

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
        attendants = new LinkedList<>();
        managers = new LinkedList<>();
        events = new LinkedList<>();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EventManager> getManagers() {
        return managers;
    }

    public void setManagers(List<EventManager> managers) {
        this.managers = managers;
    }
    
    public void addEventManager(EventManager manager) {
        managers.add(manager);
    }

    public void removeEventManager(EventManager manager) {
        managers.remove(manager);
    }

    public List<Attendant> getAttendants() {
        return attendants;
    }

    public void setAttendants(List<Attendant> attendants) {
        this.attendants = attendants;
    }
    
    public void addAttendant(Attendant attendant) {
        attendants.add(attendant);
    }

    public void removeAttendant(Attendant attendant) {
        attendants.remove(attendant);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
    
    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    @Override
    public String toString() {
        return "entities.Subject[ id=" + id + " ]";
    }
    
}
