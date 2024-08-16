package avtosalon.example.King_Motors.model;

import jakarta.persistence.*;

@Entity
@Table(name="ImageModelCrasover")
public class ImageModelCrasover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @Column(length = 500000000)
    private byte picByte;

    @Column(name = "image_link")
    private String imageLink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte getPicByte() {
        return picByte;
    }

    public void setPicByte(byte picByte) {
        this.picByte = picByte;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
