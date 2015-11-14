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
import javax.persistence.Table;

/**
 *
 * @author David
 */
@Entity
@Table(name = "ATTENDANTS")
@NamedQueries({
    @NamedQuery(name = "getAllAttendants",
    query = "SELECT a FROM Attendant a ORDER BY a.id")})
public class Attendant extends User implements Serializable {

      @ManyToMany
      @JoinTable(name = "ATTENDANT_SUBJECT",
            joinColumns
            = @JoinColumn(name = "ATTENDANT_ID", referencedColumnName = "ID"),
            inverseJoinColumns
            = @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID"))
    private List<Subject> subjects;
    
    @ManyToMany (mappedBy ="attendants")  
    private List<Event> events;
    
    
    protected Attendant() {
        subjects = new LinkedList<>();
        events = new LinkedList<>();
    }

    public Attendant(long id, String name, String password, String email) {
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
    public String toString() {
        return "Attendant{" + "username=" + id + ", password=" + password + ", name=" + name + ", email=" + email + '}';
    }
    
    
    @Override
  public String getUserType(){
    return "Attendant";
    }
    
}
