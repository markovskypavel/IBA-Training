package by.iba.markovsky.festivalorganisation.application.service;

import by.iba.markovsky.festivalorganisation.domain.entity.Artist;
import by.iba.markovsky.festivalorganisation.domain.entity.User;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.ExistException;

import java.util.ArrayList;
import java.util.List;

public class AddParticipantService {

    List<User> participants = new ArrayList<>();

    public boolean addParticipant(String name, String surname, int age, String username, String password, String email, String telephone, boolean status) {
        try {
            User participant = new User(name, surname, age, username, password, email, telephone, status);
            if (checkExist(participant)) {
                throw new ExistException("Participant exist.");
            }
            participants.add(participant);
            return true;
        } catch (ExistException ee) {
            System.out.println(ee.getMessage());
        }
        return false;
    }

    private boolean checkExist(User newParticipant) {
        for (User participant : participants) {
            if (participant.getUsername().equals(newParticipant.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public List<User> getParticipants() {
        return participants;
    }

}
