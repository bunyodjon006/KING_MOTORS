package avtosalon.example.King_Motors.model;

import jakarta.persistence.*;

@Entity
@Table(name = "imagemodel")
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Column(length = 500000000)
    private byte picByte;

    public ImageModel() {
    }

    public ImageModel(Long id, String name, String type, byte picByte) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

    public ImageModel(String originalFilename, String contentType, byte[] bytes) {
    }

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
}
