package by.iba.markovsky.festivalorganisation.presentation.controller;

import by.iba.markovsky.festivalorganisation.application.service.AddActivityService;
import by.iba.markovsky.festivalorganisation.application.service.AddArtistService;
import by.iba.markovsky.festivalorganisation.application.service.AddParticipantService;
import by.iba.markovsky.festivalorganisation.application.service.AddPlaceService;
import by.iba.markovsky.festivalorganisation.domain.entity.ActivityType;
import by.iba.markovsky.festivalorganisation.view.View;

public class Controller {

    public static void main(String[] args) {
        AddPlaceService addPlaceService = new AddPlaceService();
        AddParticipantService addParticipantService = new AddParticipantService();
        AddArtistService addArtistService = new AddArtistService();
        AddActivityService addActivityService = new AddActivityService();

        addPlaceService.addPlace("Зыбицкая 1", 2);
        addArtistService.addArtist("Clams Casino", "Witch house");
        addParticipantService.addParticipant("Pavel", "Markovsky", 20, "markovskypavel", "Qwerty123", "markovskypavel@gmail.com",
                "1234567", false);
        addParticipantService.addParticipant("Kirill", "Budnik", 21, "kir9bud", "Qwerty123", "markel@gmail.com",
                "12345678", false);
/*        addParticipantService.addParticipant("Sasha", "Vagner", 21, "vagners", "Qwerty123", "vagners@gmail.com",
                "988775", false);*/

        View.print(addActivityService.addAdctivity(ActivityType.CONCERT, "Clams Casino concert",
                addPlaceService.getPlace(),
                addParticipantService.getParticipants(),
                addArtistService.getArtists(),
                "Concert of Clams Casino"));
    }

}
