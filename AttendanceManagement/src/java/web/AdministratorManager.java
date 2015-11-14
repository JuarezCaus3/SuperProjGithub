/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dto.AttendantDTO;
import dto.EventDTO;
import dto.EventManagerDTO;
import dto.UserDTO;
import ejbs.AttendantBean;
import ejbs.EventBean;
import ejbs.EventManagerBean;
import ejbs.UserBean;
import entities.User;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;

/**
 *
 * @author David
 */
@ManagedBean
@SessionScoped
public class AdministratorManager {
    
    @EJB
    private AttendantBean attendantBean;  
    @EJB
    private EventManagerBean eventManagerBean;
    @EJB
    private EventBean eventBean;
    @EJB
    private UserBean userBean;
    
    private AttendantDTO newAttendant;
    private EventManagerDTO newEventManager;
    private EventDTO newEvent;
    private UIComponent component;
    private UserDTO currentUser;
    private String pass;
    private String valid;
    private String id;
    
    private User loggedUser;
        
        public AdministratorManager() {
            
        newAttendant = new AttendantDTO();
        newEventManager = new EventManagerDTO();
        newEvent = new EventDTO();

    }

    public UserDTO getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserDTO currentUser) {
        this.currentUser = currentUser;
    }
    
        /////////////// ATTENDANTS /////////////////
    public String createAttendant() {
        try {
            attendantBean.create(
                    newAttendant.getId(),
                    newAttendant.getPassword(),
                    newAttendant.getName(),
                    newAttendant.getEmail());
            newAttendant.reset();
            return "index?faces-redirect=true";
        } catch (Exception e) {
           System.err.println("Error: " + e.getMessage());
           return null;
        }
        
    }
    
       public List<AttendantDTO> getAllAttendants() {
        try {
            return attendantBean.getAll();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());            
            return null;
        }
    }
       
    
    //EventManager
    
    public String createEventManager(){
        try{
            eventManagerBean.create(
                    newEventManager.getId(),
                    newEventManager.getPassword(),
                    newEventManager.getName(),
                    newEventManager.getEmail());
            return "index?faces-redirect=true";
        } catch (Exception e) {
            System.err.println("Error: "+ e.getMessage());
            return null;
        }
    }
    
    public List<EventManagerDTO> getAllEventManagers(){
        try {
            return eventManagerBean.getAll();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());            
            return null;
        }
    }
    
    //////////////////////// EVENTS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    
    public String createEvent(){
        try{
            eventBean.create(
                    newEvent.getId(),
                    newEvent.getName(),
                    newEvent.getRoom(),
                    newEvent.getDate(),
                    newEvent.getHour(),
                    newEvent.getWeek(),
                    newEvent.getSubject(),
                    newEvent.getManager()
            );
            return "index?faces-redirect=true";
        } catch (Exception e) {
            System.err.println("Error: "+ e.getMessage());
            return null;
        }
    }
    
    public List<EventDTO> getAllEvents(){
        try {
            return eventBean.getAll();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());            
            return null;
        }
    }
    
    ////////////////////// Setters And Getters \\\\\\\\\\\\\\\\\\\
    
    public AttendantDTO getNewAttendant() {
        return newAttendant;
    }

    public void setNewAttendant(AttendantDTO newAttendant) {
        this.newAttendant = newAttendant;
    }
    
    public EventManagerDTO getNewEventManager() {
        return newEventManager;
    }

    public void setNewEventManager(EventManagerDTO newEventManager) {
        this.newEventManager = newEventManager;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    } 

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
    
    
    //to implement
    public String loginUser(){
        long ola1 = Long.parseLong(id);
       boolean ola=userBean.verifyPass(ola1, pass);
       
        if (!ola){ 
            valid="axxx";
            return "index?faces-redirect=true";}
        else{
        loggedUser = userBean.getUser(ola1);
        if (loggedUser.getUserType().compareTo("Admin")==0) { 
            return "home?faces-redirect=true"; }else{
          if (loggedUser.getUserType().compareTo("Attendant")==0) { 
            return "home?faces-redirect=true"; } else{
            if (loggedUser.getUserType().compareTo("manager")==0) { 
            return "home?faces-redirect=true"; } }}
        }
             valid=loggedUser.getUserType();
          return "index?faces-redirect=true";
    }
    
    public String logoutUser(){
        currentUser=null;
        return "index?faces-redirect=true";
    }
    
}
