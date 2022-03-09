package cvut.fel.dbs.lib.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "driver", schema = "public", catalog = "db21_nurmuedu")
@Access(AccessType.PROPERTY)
public class DriverEntity {
    private String driverLicenceNumber;
    private String name;
    private String surname;
    private String directorname;

    public DriverEntity(String driverLicenceNumber, String name, String surname, String directorname, String directorsurname) {
        this.driverLicenceNumber = driverLicenceNumber;
        this.name = name;
        this.surname = surname;
        this.directorname = directorname;
        this.directorsurname = directorsurname;
    }

    public DriverEntity() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDirectorname() {
        return directorname;
    }

    public void setDirectorname(String directorname) {
        this.directorname = directorname;
    }

    public String getDirectorsurname() {
        return directorsurname;
    }

    public void setDirectorsurname(String directorsurname) {
        this.directorsurname = directorsurname;
    }

    private String directorsurname;


    public List<BusEntity> getBuses() {
        return buses;
    }

    public void setBuses(List<BusEntity> buses) {
        this.buses = buses;
    }

    @ManyToMany
    @JoinTable(name = "drives",joinColumns =
    @JoinColumn(name ="driver_licence_number"), inverseJoinColumns =
    @JoinColumn(name = "car_number")
    )
    private List<BusEntity> buses;

    @Id
    @Column(name = "driver_licence_number")
    public String getDriverLicenceNumber() {
        return driverLicenceNumber;
    }

    public void setDriverLicenceNumber(String driverLicenceNumber) {
        this.driverLicenceNumber = driverLicenceNumber;
    }

    @Override
    public boolean equals(Object obj) {
        return (((DriverEntity) obj).getDriverLicenceNumber() == this.getDriverLicenceNumber() &&
                ((DriverEntity) obj).getDirectorsurname() == this.getDirectorsurname() &&
                ((DriverEntity) obj).getDirectorname() == this.getDirectorname() &&
                ((DriverEntity) obj).getSurname() == this.getSurname() &&
                ((DriverEntity) obj).getName() == this.getName());
    }

    @Override
    public int hashCode() {
        return driverLicenceNumber != null ? driverLicenceNumber.hashCode() : 0;
    }
}
