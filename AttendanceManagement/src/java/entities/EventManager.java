/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author David
 */
@Entity
public class EventManager extends User implements Serializable {

    
    protected EventManager() {
    }

    public EventManager(long id, String name, String password, String email) {
        super(id, name, password, email);
    }
}
