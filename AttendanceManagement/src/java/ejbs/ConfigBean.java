/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import java.util.Date;
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
    private EventManagerBean eventManagerBean;
    @EJB
    private AttendantBean attendantBean;
    @EJB
    private EventBean eventBean;
    @EJB
    private SubjectBean subjectBean;

    @PostConstruct
    public void populateBD() {

        try {

            attendantBean.create(123, "Manuel", "Manuel", "dae.ei.ipleiria@gmail.com");
            attendantBean.create(1234, "Antonio", "António", "dae.ei.ipleiria@gmail.com");
            attendantBean.create(12345, "Ana", "Ana", "dae.ei.ipleiria@gmail.com");
            attendantBean.create(123456, "Jose", "José", "dae.ei.ipleiria@gmail.com");
            
            subjectBean.create(1, "DAD");
            subjectBean.create(2, "DAE");
            
            eventManagerBean.create(8888, "Prof_DAE", "Prof_DAE", "prof_dae.ei.ipleiria@gmail.com");
            eventManagerBean.create(9999, "Nando", "Nando", "prof_nando.ei.ipleiria@gmail.com");
            
            Date date = new Date(2015, 11, 13);
            eventBean.create(7, "Teste", "2.2", date, 5, 6, 1, 8888);
            
            

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
