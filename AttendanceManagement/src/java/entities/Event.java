/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author David
 */
@Entity
@Table(name = "EVENTS")
@NamedQueries({
    @NamedQuery(name = "getAllEvents",
    query = "SELECT c FROM Event c ORDER BY c.id"),
    @NamedQuery(name = "getAllOpenEvents",
    query = "SELECT c FROM Event c WHERE c.status = :status ORDER BY c.id")
    })
public class Event implements Serializable {

    @Id
    private int id;
    @NotNull    
    private String name;
    @NotNull
    private String room;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;
    @NotNull
    private int hora;
    private int week;
    @NotNull
    private boolean status;
    
    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID")
    @NotNull (message="A Event must be related to a subject")
    private Subject subject;
    
    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    @NotNull (message="A Event must have an event manager")
    private EventManager manager;
    
    @ManyToMany
    @JoinTable(name = "EVENT_ATTENDANT",
            joinColumns
            = @JoinColumn(name = "EVENT_ID", referencedColumnName = "ID"),
           inverseJoinColumns
            = @JoinColumn(name = "ATTENDANT_ID", referencedColumnName = "ID"))
    private List<Attendant> attendants;
    

    public Event() {
        attendants = new LinkedList<>();
    }

    public Event(int id, String name, String room, Date date, int hora, int week, Subject subject, EventManager manager, boolean status) {
        this.id = id;
        this.name = name;
        this.room = room;      
        this.date = date;
        this.hora = hora;
        this.week = week;
        this.subject = subject;
        this.manager = manager;
        this.status = status;
        attendants = new LinkedList<>();
    }    
        
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public EventManager getManager() {
        return manager;
    }

    public void setManager(EventManager manager) {
        this.manager = manager;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
  
}
