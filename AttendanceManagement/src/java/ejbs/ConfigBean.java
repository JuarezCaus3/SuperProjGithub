/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author David
 */
@Startup
@Singleton
public class ConfigBean {

    @EJB
    private AdministratorBean administratorBean;
    @EJB
    private EventManagerBean courseBean;
    @EJB
    private AttendantBean studentBean;
    @EJB
    private EventBean subjectBean;   

    @PostConstruct
    public void populateBD() {

        try {

          

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
