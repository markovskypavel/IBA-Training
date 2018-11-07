package by.iba.markovsky.festival.util;

import by.iba.markovsky.festival.model.*;
import by.iba.markovsky.festival.model.enumeration.ActivityType;
import by.iba.markovsky.festival.model.enumeration.RoleType;
import by.iba.markovsky.festival.repository.ActivityRepository;
import by.iba.markovsky.festival.repository.WebIdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    @Qualifier("webIdentityRepository")
    private WebIdentityRepository webIdentityRepository;

    @Autowired
    @Qualifier("activityRepository")
    private ActivityRepository activityRepository;

    @Override
    public void run(String... args) throws Exception {
/*        Identity identity = new Identity("Павел", "Марковский", 21);
        WebIdentity webIdentity = new WebIdentity("markovskypavel", EncryptedPasswordUtil.encrytePassword("Qwerty123"),
                "markovskypavel@gmail.com", "80293041779",
                RoleType.ROLE_ADMIN, identity);
        Artist artist1 = new Artist("Clams", "Вичуха");
        Artist artist2 = new Artist("Drake", "Реп");
        Place place = new Place("ул. Воронянского 27", 10);
        Activity activity = new Activity(ActivityType.CONCERT, "Traphouse", place,
                new HashSet<WebIdentity>(Arrays.asList(webIdentity)),
                new HashSet<Artist>(Arrays.asList(artist1, artist2)),
                "This is concert.", new Date());
        activityRepository.save(activity);*/
    }

}
