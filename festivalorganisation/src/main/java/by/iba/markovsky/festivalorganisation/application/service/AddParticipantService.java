package by.iba.markovsky.festivalorganisation.application.service;

import by.iba.markovsky.festivalorganisation.domain.entity.User;
import by.iba.markovsky.festivalorganisation.infrastructure.exception.ExistException;

import java.util.ArrayList;
import java.util.List;

public class AddParticipantService {

    private List<User> participants = new ArrayList<>();

    public void addParticipant(String name, String surname, int age,
                               String username, String password, String email, String telephone, boolean status) throws ExistException {
        User participant = new User(name, surname, age, username, password, email, telephone, status);
        if (checkExist(participant)) {
            throw new ExistException("Participant exists");
        }
        participants.add(participant);
    }
    private boolean checkExist(User newParticipant) {
        for (User participant : participants) {
            if (participant.getWebIdentity().getUsername().equals(newParticipant.getWebIdentity().getUsername())) {
                return true;
            }
        }
        return false;
    }
    public List<User> getParticipants() {
        return participants;
    }

}
