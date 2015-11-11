/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    private  List<EventManager>     managers;
    
        @ManyToMany (mappedBy ="subjects")
        private List<Attendant> attendants;
        
        @OneToMany(mappedBy = "subject")
        private List<Event> eventos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Subject[ id=" + id + " ]";
    }
    
}
