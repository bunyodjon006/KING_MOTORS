package avtosalon.example.King_Motors.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hatchbackcar")
public class HatchbackCar {
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

    public HatchbackCar() {
    }

    public HatchbackCar(Long id, String modelname, String informationcar, String fuel, String carbox, String caryear, String cost) {
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
