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
/*@Table(name = "EVENTMANAGERS")
@NamedQueries({
    @NamedQuery(name = "getAllEventManagers",
    query = "SELECT e FROM EventManagers e ORDER BY e.name")})*/
public class EventManager extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    
    public EventManager() {
    }

    public EventManager(long id, String name, String password, String email) {
        super(id, name, password, email);
    }
}
