package by.iba.markovsky.festivalorganisation.controller;

import by.iba.markovsky.festivalorganisation.exception.LimitException;
import by.iba.markovsky.festivalorganisation.exception.ServiceException;
import by.iba.markovsky.festivalorganisation.model.entity.Activity;
import by.iba.markovsky.festivalorganisation.model.entity.ActivityType;
import by.iba.markovsky.festivalorganisation.service.ActivityHibernateService;
import by.iba.markovsky.festivalorganisation.service.ActivityService;
import by.iba.markovsky.festivalorganisation.util.database.HibernateUtil;
import by.iba.markovsky.festivalorganisation.view.View;

import java.util.Date;
import java.util.List;

public class Controller {

    public static void main(String[] args) {
        //TODO: Здесь должна проходить валидация данных при получении
        ActivityService activityService = new ActivityService();
        ActivityHibernateService activityHibernateService = new ActivityHibernateService();
        try {
/*            activityService.addPlace("Voronyanskogo", 10);
            activityService.addArtist("Clams Casino", "Witch house");
            activityService.addArtist("Drake", "Hip hop");
            activityService.addParticipant("Pavel", "Markovsky", 20,
                    "markovskypavel", "Qwerty123", "markovskypavel@gmail.com",
                    "1234567", false);
            activityService.addParticipant("Kirill", "Budnik", 21,
                    "kir9bud", "Qwerty123", "markel@gmail.com",
                    "12345678", false);
            activityService.addParticipant("Sasha", "Vagner", 21,
                    "vagners", "Qwerty123", "vagners@gmail.com",
                    "988775", false);
            activityService.addActivityInfo(ActivityType.CONCERT, "Trap house", "This is concert.", new Date());
            activityService.addAdctivityToDB();
            View.print(activityService.getAllActivities());*/

/*            activityHibernateService.addPlace("Levkova", 10);
            activityHibernateService.addArtist("Lil Pump", "Rap");
            activityHibernateService.addParticipant("Kirill", "Kuzmich", 20,
                    "kirkuz", "Qwerty123", "kirkuz@gmail.com",
                    "345623", true);
            activityHibernateService.addActivityInfo(ActivityType.CONCERT, "Rap house", "This is concert.", new Date());
            activityHibernateService.addAdctivityToDB();*/

            View.print(activityHibernateService.getAllActivities());
            HibernateUtil.closeSessionFactory();
        } catch (ServiceException /*| LimitException*/ ee) {
            View.print(ee.getMessage());
            ee.printStackTrace();
        }

        /*        FileWorker.writeToFile(activityService.getActivity());*/
        /*        View.print(FileWorker.<Activity>readFromFile());*/
    }

}
