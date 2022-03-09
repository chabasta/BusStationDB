package cvut.fel.dbs.lib.model;

import javax.persistence.*;

@Entity
@Table(name = "route", schema = "public", catalog = "db21_nurmuedu")
public class RouteEntity {

    private String number;

    @Id
    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
