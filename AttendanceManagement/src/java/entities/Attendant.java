/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
      @JoinTable(name = "SUBJECT_ATTENDANT",
            joinColumns
            = @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "SUBJECT_ID"),
            inverseJoinColumns
            = @JoinColumn(name = "ATTENDANT_ID", referencedColumnName = "ATTENDANT_ID"))
    private List<Subject> subjects;
    
    
    protected Attendant() {
    }

    public Attendant(long id, String name, String password, String email) {
        super(id, name, password, email);
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
