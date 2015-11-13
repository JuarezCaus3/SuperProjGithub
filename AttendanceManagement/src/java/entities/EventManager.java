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
import javax.persistence.OneToMany;

/**
 *
 * @author David
 */
@Entity
public class EventManager extends User implements Serializable {

    @ManyToMany
    @JoinTable(name = "SUBJECT_MANAGER",
            joinColumns
            = @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "SUBJECT_ID"),
           inverseJoinColumns
            = @JoinColumn(name = "MANAGER_ID", referencedColumnName = "MANAGER_ID"))
    private List<Subject> subjects;
    
    @OneToMany(mappedBy = "manager")
    private List<Event> events;
 
    protected EventManager() {
        subjects = new LinkedList<>();
    }

    public EventManager(long id, String name, String password, String email) {
        super(id, name, password, email);
        subjects = new LinkedList<>();
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
    
    @Override
  public String getUserType(){
    return "manager";
    }
}
