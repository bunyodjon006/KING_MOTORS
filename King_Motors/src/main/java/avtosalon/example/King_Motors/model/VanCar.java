package avtosalon.example.King_Motors.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "vancar")
public class VanCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="modelname")
    private String modelname;
    @Column(name = "informationcar")
    private String informationcar;
    @Column(name = "fuel")
    private  String fuel;
    @Column(name = "carbox")
    private  String carbox;
    @Column(name="caryear")
    private String caryear;
    @Column(name = "cost")
    private  String cost;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "product_images-select-vancar",
            joinColumns = {
                    @JoinColumn(name = "product_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "image_id")
            })
    private Set<ImageModelVanCar> productImages;

    public Set<ImageModelVanCar> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ImageModelVanCar> productImages) {
        this.productImages = productImages;
    }

    public VanCar() {
    }

    public VanCar(Long id, String modelname, String informationcar, String fuel, String carbox, String caryear, String cost) {
        this.id = id;
        this.modelname = modelname;
        this.informationcar = informationcar;
        this.fuel = fuel;
        this.carbox = carbox;
        this.caryear = caryear;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getInformationcar() {
        return informationcar;
    }

    public void setInformationcar(String informationcar) {
        this.informationcar = informationcar;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getCarbox() {
        return carbox;
    }

    public void setCarbox(String carbox) {
        this.carbox = carbox;
    }

    public String getCaryear() {
        return caryear;
    }

    public void setCaryear(String caryear) {
        this.caryear = caryear;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
