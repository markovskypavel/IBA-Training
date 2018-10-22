package by.iba.markovsky.festivalorganisation.domain.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Activity implements Serializable {

    private static final long serialVersionUID = -6763387266714169960L;

    private int id;

    private ActivityType activityType = ActivityType.FESTIVAL;
    private String name;
    private Place place;
    private List<User> participants;
    private List<Artist> artists;
    private String description;

    public Activity() {

    }
    public Activity(ActivityType activityType, String name, Place place, List<User> participants, List<Artist> artists, String description) {
        this.activityType = activityType;
        this.name = name;
        this.place = place;
        this.participants = participants;
        this.artists = artists;
        this.description = description;
    }
    public Activity(Activity activity){
        this.activityType = activity.activityType;
        this.name = activity.name;
        this.place = activity.place;
        this.participants = activity.participants;
        this.artists = activity.artists;
        this.description = activity.description;
    }

    //Setters

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }
    public void changeDefaultActivityType(){
        this.activityType = ActivityType.CONCERT;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPlace(Place place) {
        this.place = place;
    }
    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    //Getters
    public int getId() {
        return id;
    }
    public ActivityType getActivityType() {
        return activityType;
    }
    public String getName() {
        return name;
    }
    public Place getPlace() {
        return place;
    }
    public List<User> getParticipants() {
        return participants;
    }
    public List<Artist> getArtists() {
        return artists;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return id == activity.id &&
                activityType == activity.activityType &&
                Objects.equals(name, activity.name) &&
                Objects.equals(place, activity.place) &&
                Objects.equals(participants, activity.participants) &&
                Objects.equals(artists, activity.artists) &&
                Objects.equals(description, activity.description);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, activityType, name, place, participants, artists, description);
    }
    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityType=" + activityType +
                ", name='" + name + '\'' +
                ", place=" + place +
                ", participants=" + participants +
                ", artists=" + artists +
                ", description='" + description + '\'' +
                '}';
    }

}
