package cvut.fel.dbs.lib.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bus", schema = "public", catalog = "db21_nurmuedu")
public class BusEntity {

    public BusEntity(String car_number) {
        this.car_number = car_number;
    }

    public BusEntity() {

    }

    public BusEntity(String car_number, String number) {
        this.car_number = car_number;
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        return (((BusEntity) obj).brand == this.brand &&
                ((BusEntity) obj).car_number == this.getCar_number() &&
                ((BusEntity) obj).size == this.size &&
                ((BusEntity) obj).years_in_use == this.years_in_use &&
                ((BusEntity) obj).number == this.number);
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setYears_in_use(String years_in_use) {
        this.years_in_use = years_in_use;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BusEntity(String car_number, String number, String size, String years_in_use, String brand) {
        this.car_number = car_number;
        this.number = number;
        this.size = size;
        this.years_in_use = years_in_use;
        this.brand = brand;
    }

    public List<DriverEntity> getDrivers() {
        return drivers;
    }

    @ManyToMany(mappedBy = "buses")
    private List<DriverEntity> drivers;

    @Id
    @Column(name="car_number")
    private String car_number;

    @Column(name = "number")
    private String number;

    private String size;

    public String getYears_in_use() {
        return years_in_use;
    }

    public String getBrand() {
        return brand;
    }

    private String years_in_use;

    private String brand;

    public String getNumber() {
        return number;
    }

    public String getSize() {
        return size;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public void setDrivers(List<DriverEntity> drivers) {
        this.drivers = drivers;
    }
}
