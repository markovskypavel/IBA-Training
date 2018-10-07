package by.iba.markovsky.festivalorganisation.application.service;

import by.iba.markovsky.festivalorganisation.domain.entity.Place;

public class AddPlaceService {

    private Place place = null;

    public void addPlace(String address, int capacity) {
        place = new Place(address, capacity);
    }
    public Place getPlace() {
        return place;
    }

}
