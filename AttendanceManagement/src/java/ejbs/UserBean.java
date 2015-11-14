/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import dto.AdministratorDTO;
import dto.UserDTO;
import entities.Administrator;
import entities.Attendant;
import entities.EventManager;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kazhw
 */
@Stateless
public class UserBean {

      @PersistenceContext
    private EntityManager em;

      public boolean verifyPass(long id, String pass){
      try {
            User user = em.find(Attendant.class, id);
            if(user == null){
                
                user = em.find(Administrator.class, id);
                     if(user == null){
                         
                        user = em.find(EventManager.class, id);
                     if(user==null){return false;}
                     }
            }
           return true;
           //if (user.getPassword().compareTo(pass)==0){return true;}
           //return false;
                
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
      }

    public User getUser(long id) {
        try {
            User user = em.find(Attendant.class, id);
            if(user != null){
            return user;}
             user = em.find(Administrator.class, id);
             if(user != null){
            return user;}
              user = em.find(EventManager.class, id);
                return user;
                
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void update(long id, String password, String name, String email) {
        try {
            
           User user = em.find(User.class, id);
            if (user == null) {
               throw new EJBException("This administrator id does not exist");
            }
           
            user.setPassword(password);
            user.setName(name);
            user.setEmail(email);
            em.merge(user);
                     
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void remove(long id) {
        try {

            User user = em.find(User.class, id);
            if (user == null) {
               throw new EJBException("This administrator id does not exist");
            }
                                
            em.remove(user);
        
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
    
     public List<UserDTO> getAll() {
        try {
            List<User> user = (List<User>) em.createNamedQuery("getAllUsers").getResultList();
            return userToDTOs(user);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }
        
            UserDTO userToDTO(User user) {
        return new UserDTO(
                user.getId(),
                null,
                user.getName(),
                user.getEmail());
    }

    List<UserDTO> userToDTOs(List<User> users) {
        List<UserDTO> dtos = new ArrayList<>();
        for (User a : users) {
            dtos.add(userToDTO(a));
        }
        return dtos;
    }
}
