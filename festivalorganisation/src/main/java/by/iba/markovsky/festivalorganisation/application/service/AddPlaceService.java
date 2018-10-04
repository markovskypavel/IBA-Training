package by.iba.markovsky.festivalorganisation.application.service;

import by.iba.markovsky.festivalorganisation.domain.entity.Place;

public class AddPlaceService {

    Place place;

    public boolean addPlace(String address, int capacity) {
        place = new Place(address, capacity);
        return true;
    }

    public Place getPlace() {
        return place;
    }

}
