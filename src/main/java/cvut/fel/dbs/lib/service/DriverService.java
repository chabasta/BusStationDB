package cvut.fel.dbs.lib.service;

import cvut.fel.dbs.lib.dao.DriverEntityDao;
import cvut.fel.dbs.lib.model.DriverEntity;
import cvut.fel.dbs.lib.model.DrivesEntity;

import java.util.List;

public class DriverService {

    DriverEntityDao dao;

    public DriverService(DriverEntityDao dao){
        this.dao = dao;
    }

    public Object[][] allToRows(){
        List<DriverEntity> drivers = dao.findAll();
        Object[][] rowsDriver = new Object[drivers.size()][5];
        for (int i =0; i < drivers.size(); i++) {
            rowsDriver[i] = new Object[]{
                    drivers.get(i).getName(),
                    drivers.get(i).getSurname(),
                    drivers.get(i).getDirectorname(),
                    drivers.get(i).getDirectorsurname(),
                    drivers.get(i).getDriverLicenceNumber(),
            };
        }
        return rowsDriver;
    }

}
