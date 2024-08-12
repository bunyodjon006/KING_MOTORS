package avtosalon.example.King_Motors.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.EnableMBeanExport;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "email")
    private String email;
    @Column(name = "addrees")
    private String addrees;
    @Column(name = "key")
    private  String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public User() {
    }

    public User(Long id, String name, String firstname, String email, String addrees) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.email = email;
        this.addrees = addrees;
        this.key=key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddrees() {
        return addrees;
    }

    public void setAddrees(String addrees) {
        this.addrees = addrees;
    }
}
