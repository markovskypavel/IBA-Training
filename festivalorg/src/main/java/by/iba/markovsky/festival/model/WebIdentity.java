package by.iba.markovsky.festival.model;

import by.iba.markovsky.festival.constant.RegExConstant;
import by.iba.markovsky.festival.model.enumeration.RoleType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@ApiModel(description="WebIdentity")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@XmlRootElement(name = "WebIdentity")
@XmlType(propOrder = {"id","username","unencryptedPassword","password","email","telephone","roleType","identity"})
@XmlSeeAlso({Identity.class, RoleType.class})
@Entity
@Table(name = "WebIdentity")
public class WebIdentity implements Serializable {

    private static final long serialVersionUID = 3276480509050536113L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "webIdentity_id", unique = true, updatable = false)
    private int id;

    @Pattern(regexp = RegExConstant.LOGIN)
    @Column(name = "username", nullable = false)
    private String username;

    @Transient
    @Pattern(regexp = RegExConstant.PASSWORD)
    private String unencryptedPassword;

    @Size(min = 5)
    @Column(name = "password", nullable = false)
    private String password;

    @Pattern(regexp = RegExConstant.EMAIL)
    @Column(name = "email", nullable = false)
    private String email;

    @Pattern(regexp = RegExConstant.TELEPHONE_ALTERNATIVE)
    @Column(name = "telephone")
    private String telephone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleType roleType = RoleType.ROLE_USER;

    @Valid
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "identity_id", nullable = false)
    private Identity identity = new Identity();

    //Лучше убрать many to many.
    // В angular у каждого поля права на доступ, для сервера существуют отдельные от клиента сущности
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER, mappedBy = "users")
    private Set<Activity> activities = new HashSet<>();

    public WebIdentity() {
    }
    public WebIdentity(int id, String username, String unencryptedPassword, String email, String telephone, RoleType roleType) {
        this.id = id;
        this.username = username;
        this.unencryptedPassword = unencryptedPassword;
        this.email = email;
        this.telephone = telephone;
        this.roleType = roleType;
    }
    public WebIdentity(int id, String username, String unencryptedPassword, String email, String telephone, RoleType roleType, Identity identity, Set<Activity> activities) {
        this.id = id;
        this.username = username;
        this.unencryptedPassword = unencryptedPassword;
        this.email = email;
        this.telephone = telephone;
        this.roleType = roleType;
        this.identity = identity;
        this.activities = activities;
    }
    public WebIdentity(int id, String username, String unencryptedPassword, String email, String telephone, RoleType roleType, Identity identity) {
        this.id = id;
        this.username = username;
        this.unencryptedPassword = unencryptedPassword;
        this.email = email;
        this.telephone = telephone;
        this.roleType = roleType;
        this.identity = identity;
    }
    public WebIdentity(int id, int idIdentity, String username, String unencryptedPassword, String email, String telephone, RoleType roleType) {
        this.id = id;
        this.username = username;
        this.unencryptedPassword = unencryptedPassword;
        this.email = email;
        this.telephone = telephone;
        this.roleType = roleType;
        this.identity = new Identity(idIdentity);
    }
    public WebIdentity(String username, String unencryptedPassword, String email, String telephone, RoleType roleType) {
        this.username = username;
        this.unencryptedPassword = unencryptedPassword;
        this.email = email;
        this.telephone = telephone;
        this.roleType = roleType;
    }
    public WebIdentity(String username, String unencryptedPassword, String email, String telephone, RoleType roleType, Identity identity) {
        this.username = username;
        this.unencryptedPassword = unencryptedPassword;
        this.email = email;
        this.telephone = telephone;
        this.roleType = roleType;
        this.identity = identity;
    }
    public WebIdentity(String username, String unencryptedPassword, String email, String telephone, RoleType roleType, Identity identity, Set<Activity> activities) {
        this.username = username;
        this.unencryptedPassword = unencryptedPassword;
        this.email = email;
        this.telephone = telephone;
        this.roleType = roleType;
        this.identity = identity;
        this.activities = activities;
    }
    public WebIdentity(String username, String unencryptedPassword, String password, String email, String telephone, RoleType roleType, Identity identity, Set<Activity> activities) {
        this.username = username;
        this.unencryptedPassword = unencryptedPassword;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.roleType = roleType;
        this.identity = identity;
        this.activities = activities;
    }
    public WebIdentity(String username, String unencryptedPassword, String password, String email, String telephone, RoleType roleType, Identity identity) {
        this.username = username;
        this.unencryptedPassword = unencryptedPassword;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.roleType = roleType;
        this.identity = identity;
    }
    public WebIdentity(WebIdentity webIdentity) {
        this.username = webIdentity.username;
        this.password = webIdentity.password;
        this.email = webIdentity.email;
        this.telephone = webIdentity.telephone;
        this.roleType = webIdentity.roleType;
        this.identity = webIdentity.identity;
        this.activities = webIdentity.activities;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUnencryptedPassword(String unencryptedPassword) {
        this.unencryptedPassword = unencryptedPassword;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }
    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    //Getters
    @XmlElement
    public int getId() {
        return id;
    }
    @XmlElement
    public String getUsername() {
        return username;
    }
    @XmlElement
    public String getPassword() {
        return password;
    }
    @XmlElement
    public String getUnencryptedPassword() {
        return unencryptedPassword;
    }
    @XmlElement
    public String getEmail() {
        return email;
    }
    @XmlElement
    public String getTelephone() {
        return telephone;
    }
    @XmlElement
    public RoleType getRoleType() {
        return roleType;
    }
    @XmlElement(name = "identity")
    public Identity getIdentity() {
        return identity;
    }
    /*@JsonIgnore*/
    @XmlTransient
    public Set<Activity> getActivities() {
        return activities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebIdentity that = (WebIdentity) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(unencryptedPassword, that.unencryptedPassword) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email) &&
                Objects.equals(telephone, that.telephone) &&
                roleType == that.roleType &&
                Objects.equals(identity, that.identity) &&
                Objects.equals(activities, that.activities);
    }
    //В Many-To-Many убрать в hashcode и tostring соответствующие поля по избежание рекурсии
    @Override
    public int hashCode() {
        return Objects.hash(id, username, unencryptedPassword, password, email, telephone, roleType, identity);
    }
    @Override
    public String toString() {
        return "WebIdentity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", unencryptedPassword='" + unencryptedPassword + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", roleType=" + roleType +
                ", identity=" + identity +
                '}';
    }

}
