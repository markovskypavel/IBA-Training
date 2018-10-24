package by.iba.markovsky.festivalorganisation.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Activity implements Serializable {

    private static final long serialVersionUID = -6763387266714169960L;

    private int id;

    private ActivityType activityType = ActivityType.FESTIVAL;
    private String name;
    private Place place;
    private List<WebIdentity> users;
    private List<Artist> artists;
    private String description;
    private Date date;

    public Activity() {
        this.artists = new ArrayList<>();
        this.users = new ArrayList<>();
    }
    public Activity(int id, ActivityType activityType, String name, int idPlace, String description, Date date) {
        this.id = id;
        this.activityType = activityType;
        this.name = name;
        this.place = new Place(idPlace);
        this.description = description;
        this.date = date;
    }
    public Activity(int id, ActivityType activityType, String name, Place place, String description, Date date) {
        this.id = id;
        this.activityType = activityType;
        this.name = name;
        this.place = place;
        this.description = description;
        this.date = date;
    }
    public Activity(int id, ActivityType activityType, String name, Place place, List<WebIdentity> users, List<Artist> artists, String description, Date date) {
        this.id = id;
        this.activityType = activityType;
        this.name = name;
        this.place = place;
        this.users = users;
        this.artists = artists;
        this.description = description;
        this.date = date;
    }
    public Activity(ActivityType activityType, String name, Place place, List<WebIdentity> users, List<Artist> artists, String description, Date date) {
        this.activityType = activityType;
        this.name = name;
        this.place = place;
        this.users = users;
        this.artists = artists;
        this.description = description;
        this.date = date;
    }
    public Activity(Activity activity){
        this.activityType = activity.activityType;
        this.name = activity.name;
        this.place = activity.place;
        this.users = activity.users;
        this.artists = activity.artists;
        this.description = activity.description;
        this.date = activity.date;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setDate(Date date) {
        this.date = date;
    }
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
    public void setUsers(List<WebIdentity> users) {
        this.users = users;
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
    public Date getDate() {
        return date;
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
    public List<WebIdentity> getUsers() {
        return users;
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
                Objects.equals(users, activity.users) &&
                Objects.equals(artists, activity.artists) &&
                Objects.equals(description, activity.description) &&
                Objects.equals(date, activity.date);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, activityType, name, place, users, artists, description, date);
    }
    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", activityType=" + activityType +
                ", name='" + name + '\'' +
                ", place=" + place +
                ", users=" + users +
                ", artists=" + artists +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }

}
