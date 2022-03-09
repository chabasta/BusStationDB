package cvut.fel.dbs.lib.model;

import javax.persistence.*;

@Entity
@Table(name = "drives", schema = "public", catalog = "db21_nurmuedu")
public class DrivesEntity {

    private String driver_licence_number;
    private String car_number;

    @Override
    public boolean equals(Object obj) {
        return (((DrivesEntity)obj).getCar_number() == this.getCar_number() &&
                ((DrivesEntity) obj).getDriver_licence_number() == this.getDriver_licence_number());
    }

    public DrivesEntity(String driver_licence_number, String car_number) {
        this.driver_licence_number = driver_licence_number;
        this.car_number = car_number;
    }

    public DrivesEntity() {
    }

    @Id
    public String getDriver_licence_number() {
        return driver_licence_number;
    }

    public void setDriver_licence_number(String driverLicenceNumber) {
        this.driver_licence_number = driverLicenceNumber;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String carNumber) {
        this.car_number = carNumber;
    }

}
