/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author asus
 */
public class EventManagerDTO extends UserDTO implements Serializable{
   
    public EventManagerDTO(){
    }

    public EventManagerDTO(
            long id,
            String name,
            String password,
            String email) {
        super(id, name, password, email);

    }
    
    @Override
    public void reset() {
        super.reset();
    }
    
}
