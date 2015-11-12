/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
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

    protected Attendant() {
    }

    public Attendant(long id, String name, String password, String email) {
        super(id, name, password, email);
    }
    
    @Override
    public String toString() {
        return "Attendant{" + "username=" + id + ", password=" + password + ", name=" + name + ", email=" + email + '}';
    }
    
}
