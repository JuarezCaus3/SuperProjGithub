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
    @EJB
    private AdministratorBean administratorBean;

    @PostConstruct
    public void populateBD() {

        try {
            
            administratorBean.create(9129, "admin", "David", "god@emailOfGod.pt");
            administratorBean.create(9149, "admin", "Hugo", "scrub@emailOfScrub.pt");
            administratorBean.create(9137, "admin", "Rino", "rino@email.pt");

            attendantBean.create(123, "Manuel", "Manuel", "dae.ei.ipleiria@gmail.com");
            attendantBean.create(1234, "Antonio", "António", "dae.ei.ipleiria@gmail.com");
            attendantBean.create(12345, "Ana", "Ana", "dae.ei.ipleiria@gmail.com");
            attendantBean.create(123456, "Jose", "José", "dae.ei.ipleiria@gmail.com");
            
            subjectBean.create(1, "DAD");
            subjectBean.create(2, "DAE");
            subjectBean.create(3, "F4");
            subjectBean.create(4, "DOTA2");
            subjectBean.create(5, "DAI");
            
            eventManagerBean.create(6666, "Saitama", "onepunchman", "saitama@op.com");
            eventManagerBean.create(7777, "MasterRace", "pcmaster", "theone@masterrace.pc");
            eventManagerBean.create(8888, "Prof_DAE", "Prof_DAE", "prof_dae.ei.ipleiria@gmail.com");
            eventManagerBean.create(9999, "Nando", "Nando", "prof_nando.ei.ipleiria@gmail.com");
            
            Date date = new Date(2015, 11, 13);
            eventBean.create(7, "Teste", "2.2", date, 5, 6, 2, 8888, true);
            eventBean.create(8, "Exame DAD", "2.6", date, 6, 6, 1, 9999, true);
            eventBean.create(9, "DAE cenas", "2.2", date, 7, 6, 2, 8888, false);
            
            

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
