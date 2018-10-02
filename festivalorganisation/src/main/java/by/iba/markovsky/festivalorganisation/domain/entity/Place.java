package by.iba.markovsky.festivalorganisation.domain.entity;

import java.util.Objects;

public class Place {

    private String address;
    private int capacity;

    public Place() {

    }
    public Place(String address, int capacity) {
        this.address = address;
        this.capacity = capacity;
    }
    public Place(Place place){
        this.address = place.address;
        this.capacity = place.capacity;
    }

    //Setters
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    //Getters
    public String getAddress() {
        return address;
    }
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return capacity == place.capacity &&
                Objects.equals(address, place.address);
    }
    @Override
    public int hashCode() {
        return Objects.hash(address, capacity);
    }
    @Override
    public String toString() {
        return "Place{" +
                "address='" + address + '\'' +
                ", capacity=" + capacity +
                '}';
    }

}
