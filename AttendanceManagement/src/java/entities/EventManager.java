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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author David
 */
@Entity
@Table(name = "MANAGERS")
@NamedQueries({
    @NamedQuery(name = "getAllEventManagers",
    query = "SELECT c FROM EventManager c ORDER BY c.id"),
    })
public class EventManager extends User implements Serializable {

    @ManyToMany
    @JoinTable(name = "MANAGER_SUBJECT",
            joinColumns
            = @JoinColumn(name = "MANAGER_ID", referencedColumnName = "ID"),
           inverseJoinColumns
            = @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID"))
    private List<Subject> subjects;
    
    @OneToMany(mappedBy = "manager")
    private List<Event> events;
 
    protected EventManager() {
        subjects = new LinkedList<>();
        events = new LinkedList<>();
    }

    public EventManager(long id, String name, String password, String email) {
        super(id, name, password, email);
        subjects = new LinkedList<>();
        events = new LinkedList<>();
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
    
    public void addSubject(Subject subject) {
        subjects.add(subject);
    }
    
    public void removeSubject(Subject subject) {
        subjects.remove(subject);
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
  public String getUserType(){
    return "manager";
    }
}
