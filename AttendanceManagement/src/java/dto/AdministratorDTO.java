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
public class AdministratorDTO extends UserDTO implements Serializable{
   
    public AdministratorDTO(){
    }

    public AdministratorDTO(
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
