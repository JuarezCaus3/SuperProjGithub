/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
    })
public class Event implements Serializable {

    @Id
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID")
    @NotNull (message="A Event must be related to a subject")
    private Subject subject;
        
    @NotNull    
    private String name;
    @NotNull
    private String room;
    @NotNull
    private Date date;
    @NotNull
    private int hour;
    private int week;

    public Event() {
    }

    public Event(int id, String name, String room, Date date, int hour, int week, Subject subject) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.date = date;
        this.hour = hour;
        this.week = week;
        this.subject = subject;
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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
    
}
