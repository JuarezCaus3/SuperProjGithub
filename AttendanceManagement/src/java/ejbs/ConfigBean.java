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
    private EventManagerBean eventManagerBean;
    @EJB
    private AttendantBean attendantBean;
    @EJB
    private EventBean eventBean;   

    @PostConstruct
    public void populateBD() {

        try {

            attendantBean.create(123, "Manuel", "Manuel", "dae.ei.ipleiria@gmail.com");
            attendantBean.create(1234, "Antonio", "António", "dae.ei.ipleiria@gmail.com");
            attendantBean.create(12345, "Ana", "Ana", "dae.ei.ipleiria@gmail.com");
            attendantBean.create(123456, "Jose", "José", "dae.ei.ipleiria@gmail.com");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
