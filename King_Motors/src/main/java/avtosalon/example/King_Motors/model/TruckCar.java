package avtosalon.example.King_Motors.model;

import jakarta.persistence.*;
@Entity
@Table(name = "truckar")
public class TruckCar {
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

    public TruckCar() {
    }

    public TruckCar(String carbox, Long id, String modelname, String informationcar, String fuel, String caryear, String cost) {
        this.carbox = carbox;
        this.id = id;
        this.modelname = modelname;
        this.informationcar = informationcar;
        this.fuel = fuel;
        this.caryear = caryear;
        this.cost = cost;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
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
