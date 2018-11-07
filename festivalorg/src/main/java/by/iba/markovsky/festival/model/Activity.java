package by.iba.markovsky.festival.model;

import by.iba.markovsky.festival.constant.RegExConstant;
import by.iba.markovsky.festival.model.enumeration.ActivityType;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XmlRootElement(name = "Activity")
@XmlType(propOrder = {"name","description","date","place","users","artists","activityType"})
@XmlSeeAlso({Artist.class, Place.class, WebIdentity.class, ActivityType.class})
@Entity
@Table(name = "Activity")
public class Activity implements Serializable {

    private static final long serialVersionUID = -6763387266714169960L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id", unique = true, updatable = false)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "activityType", nullable = false)
    private ActivityType activityType = ActivityType.FESTIVAL;

    @Pattern(regexp = RegExConstant.UNIQUE_NAME)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Date date;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "place_id", nullable = false)
    private Place place = new Place();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER )
    @JoinTable(
            name="Activity_has_WebIdentity",
            joinColumns = {@JoinColumn(name="activity_id")},
            inverseJoinColumns = {@JoinColumn(name="webIdentity_id")}
    )
    private Set<WebIdentity> users = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER )
    @JoinTable(
            name="Activity_has_Artist",
            joinColumns = {@JoinColumn(name="activity_id")},
            inverseJoinColumns = {@JoinColumn(name="artist_id")}
    )
    private Set<Artist> artists = new HashSet<>();

    public Activity() {
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
    public Activity(int id, ActivityType activityType, String name, Place place, Set<WebIdentity> users, Set<Artist> artists, String description, Date date) {
        this.id = id;
        this.activityType = activityType;
        this.name = name;
        this.place = place;
        this.users = users;
        this.artists = artists;
        this.description = description;
        this.date = date;
    }
    public Activity(ActivityType activityType, String name, Place place, Set<WebIdentity> users, Set<Artist> artists, String description, Date date) {
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
    public void setUsers(Set<WebIdentity> users) {
        this.users = users;
    }
    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    //Getters
    @XmlTransient
    public int getId() {
        return id;
    }
    @XmlElement
    public Date getDate() {
        return date;
    }
    @XmlElement
    public ActivityType getActivityType() {
        return activityType;
    }
    @XmlElement
    public String getName() {
        return name;
    }
    @XmlElement
    public Place getPlace() {
        return place;
    }
    @XmlElement(name = "users")
    @XmlElementWrapper
    public Set<WebIdentity> getUsers() {
        return users;
    }
    @XmlElement(name = "artists")
    @XmlElementWrapper
    public Set<Artist> getArtists() {
        return artists;
    }
    @XmlElement
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
