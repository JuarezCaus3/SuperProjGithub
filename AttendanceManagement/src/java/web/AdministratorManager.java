/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dto.AdministratorDTO;
import dto.AttendantDTO;
import dto.EventDTO;
import dto.EventManagerDTO;
import dto.SubjectDTO;
import dto.UserDTO;
import ejbs.AdministratorBean;
import ejbs.AttendantBean;
import ejbs.EventBean;
import ejbs.EventManagerBean;
import ejbs.SubjectBean;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

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
    private SubjectBean subjectBean;
    @EJB
    private AdministratorBean administratorBean; 
    
    private AttendantDTO newAttendant;
    private AttendantDTO currentAttendant;
    private EventManagerDTO newEventManager;
    private EventManagerDTO currentEventManager;
    private EventDTO newEvent;
    private EventDTO currentEvent;
    private SubjectDTO newSubject;
    private SubjectDTO currentSubject;
    private AdministratorDTO newAdministrator;
    private AdministratorDTO currentAdministrator;
    private UIComponent component;
    private UserDTO currentUser;
    private String username,pass;
        
        public AdministratorManager() {
            
        newAttendant = new AttendantDTO();
        newEventManager = new EventManagerDTO();
        newEvent = new EventDTO();
        newSubject = new SubjectDTO();

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
            return "home?faces-redirect=true";
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
       
    public String updateAttendant() {
        try {
            attendantBean.update(
                    currentAttendant.getId(),
                    currentAttendant.getPassword(),
                    currentAttendant.getName(),
                    currentAttendant.getEmail());
            return "home?faces-redirect=true";
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return "admin_user_update";
    }

    public void removeAttendant(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("attendantId");
            String value = param.getValue().toString();
            long id = Long.parseLong(value);
            attendantBean.remove(id);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public List<SubjectDTO> getCurrentAttendantsSubjects() {
        try {
            return subjectBean.getAttendantSubjects(currentAttendant.getId());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }
    
            public List<EventDTO> getCurrentAttendantsEvents() {
        try {
            return eventBean.getAttendantEvents(currentAttendant.getId());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }
       
    
    ///////////////////////// EventManager \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    
    public String createEventManager(){
        try{
            eventManagerBean.create(
                    newEventManager.getId(),
                    newEventManager.getPassword(),
                    newEventManager.getName(),
                    newEventManager.getEmail());
            return "home?faces-redirect=true";
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
    
    public String updateEventManager() {
        try {
            eventManagerBean.update(
                    currentEventManager.getId(),
                    currentEventManager.getPassword(),
                    currentEventManager.getName(),
                    currentEventManager.getEmail());
            return "home?faces-redirect=true";
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return "admin_managers_update";
    }

    public void removeEventManager(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("managerId");
            String value = param.getValue().toString();
            long id = Long.parseLong(value);
            eventManagerBean.remove(id);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public List<SubjectDTO> getCurrentEventManagersSubjects() {
        try {
            return subjectBean.getEventManagerSubjects(currentEventManager.getId());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }
    
        public List<EventDTO> getCurrentEventManagersEvents() {
        try {
            return eventBean.getEventManagerEvents(currentEventManager.getId());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    //////////////////////// EVENTS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    
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
            return "home?faces-redirect=true";
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
    
    public void removeEvent(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("subjectCode");
            int code = Integer.parseInt(param.getValue().toString());
            eventBean.remove(code);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()); 
        }
    }
    
    public List<AttendantDTO> getCheckedInAttendants() {
        try {
            return attendantBean.getCheckedInAttendants(currentEvent.getId());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()); 
        }
        return null;
    }
    
    public List<AttendantDTO> getCheckedOutAttendants() {
        try {
            return attendantBean.getCheckedOutAttendants(currentEvent.getId());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()); 
        }
        return null;
    }
    
    public void checkInAttendant(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("attendantId");
            String value = param.getValue().toString();
            long id = Long.parseLong(value);
            attendantBean.checkInAttendant(id, currentEvent.getId());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()); 
        }
    }    
    
    public void checkOutAttendant(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("attendantId");
            String value = param.getValue().toString();
            long id = Long.parseLong(value);
            attendantBean.checkOutAttendant(id, currentEvent.getId());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()); 
        }
    }
    
     /////////////// SUBJECTS /////////////////
    public String createSubject() {
        try {
            subjectBean.create(
                    newSubject.getId(),
                    newSubject.getName());
            newSubject.reset();
            return "home?faces-redirect=true";
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()); 
        }
        return null;
    }

    public List<SubjectDTO> getAllSubjects() {
        try {
            return subjectBean.getAll();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()); 
            return null;
        }
    }

    public void removeSubject(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("subjectCode");
            int code = Integer.parseInt(param.getValue().toString());
            subjectBean.remove(code);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()); 
        }
    }
    
    public List<AttendantDTO> getEnrolledAttendants() {
        try {
            return attendantBean.getEnrolledAttendants(currentSubject.getId());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()); 
        }
        return null;
    }
    
    public List<AttendantDTO> getUnrolledAttendants() {
        try {
            return attendantBean.getUnrolledAttendants(currentSubject.getId());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()); 
        }
        return null;
    }
    
    public void enrollAttendant(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("attendantId");
            String value = param.getValue().toString();
            long id = Long.parseLong(value);
            attendantBean.enrollAttendant(id, currentSubject.getId());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()); 
        }
    }    
    
    public void unrollAttendant(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("attendantId");
            String value = param.getValue().toString();
            long id = Long.parseLong(value);
            attendantBean.unrollAttendant(id, currentSubject.getId());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()); 
        }
    }
    
    //////////////////////// ADMINISTRATORS \\\\\\\\\\\\\\\\\\\\\\\\
    
    public String createAdministrator(){
        try{
            administratorBean.create(
                    newAdministrator.getId(),
                    newAdministrator.getPassword(),
                    newAdministrator.getName(),
                    newAdministrator.getEmail());
            return "home?faces-redirect=true";
        } catch (Exception e) {
            System.err.println("Error: "+ e.getMessage());
            return null;
        }
    }
    
    public List<AdministratorDTO> getAllAdministrators(){
        try {
            return administratorBean.getAll();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());            
            return null;
        }
    }
    
    public String updateAdministrator() {
        try {
            administratorBean.update(
                    newAdministrator.getId(),
                    newAdministrator.getPassword(),
                    newAdministrator.getName(),
                    newAdministrator.getEmail());
            return "home?faces-redirect=true";
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return "admin_administrators_update";
    }

    public void removeAdministrator(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("administratorId");
            String value = param.getValue().toString();
            long id = Long.parseLong(value);
            administratorBean.remove(id);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
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

    public AttendantDTO getCurrentAttendant() {
        return currentAttendant;
    }

    public void setCurrentAttendant(AttendantDTO currentAttendant) {
        this.currentAttendant = currentAttendant;
    }

    public EventManagerDTO getCurrentEventManager() {
        return currentEventManager;
    }

    public void setCurrentEventManager(EventManagerDTO currentEventManager) {
        this.currentEventManager = currentEventManager;
    }

    public EventDTO getNewEvent() {
        return newEvent;
    }

    public void setNewEvent(EventDTO newEvent) {
        this.newEvent = newEvent;
    }

    public EventDTO getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(EventDTO currentEvent) {
        this.currentEvent = currentEvent;
    }

    public SubjectDTO getNewSubject() {
        return newSubject;
    }

    public void setNewSubject(SubjectDTO newSubject) {
        this.newSubject = newSubject;
    }

    public SubjectDTO getCurrentSubject() {
        return currentSubject;
    }

    public void setCurrentSubject(SubjectDTO currentSubject) {
        this.currentSubject = currentSubject;
    }

    public AdministratorDTO getNewAdministrator() {
        return newAdministrator;
    }

    public void setNewAdministrator(AdministratorDTO newAdministrator) {
        this.newAdministrator = newAdministrator;
    }

    public AdministratorDTO getCurrentAdministrator() {
        return currentAdministrator;
    }

    public void setCurrentAdministrator(AdministratorDTO currentAdministrator) {
        this.currentAdministrator = currentAdministrator;
    } 
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    
    
    //to implement
    public String loginUser(){
        //getUserlogin
        //switchcase ? ifs 
        
        return "";    
    }
    
    public String logoutUser(){
        currentUser=null;
        return "index?faces-redirect=true";
    }
    
}
