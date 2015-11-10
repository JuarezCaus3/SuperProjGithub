/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dto.AttendantDTO;
import dto.EventManagerDTO;
import ejbs.AttendantBean;
import ejbs.EventManagerBean;
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
    
    private AttendantDTO newAttendant;
    private EventManagerDTO newEventManager;
    private UIComponent component;
    
        public AdministratorManager() {
            
        newAttendant = new AttendantDTO();
        newEventManager = new EventManagerDTO();

    }
    
        /////////////// ATTENDANTS /////////////////
    public String createAttendant() {
        try {
            attendantBean.create(
                    newAttendant.getId(),
                    newAttendant.getPassword(),
                    newAttendant.getName(),
                    newAttendant.getEmail());
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
       
       
       /////////////////// Getter Ans Setters \\\\\\\\\\\\\\\\\
       
    public AttendantDTO getNewAttendant() {
        return newAttendant;
    }

    public void setNewAttendant(AttendantDTO newAttendant) {
        this.newAttendant = newAttendant;
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
    
    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    } 
    
}
