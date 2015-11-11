/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
    
}
