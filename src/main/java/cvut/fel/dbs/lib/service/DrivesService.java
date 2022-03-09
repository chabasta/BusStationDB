package cvut.fel.dbs.lib.service;

import cvut.fel.dbs.lib.dao.BusEntityDao;
import cvut.fel.dbs.lib.dao.DrivesEntityDao;
import cvut.fel.dbs.lib.model.BusEntity;
import cvut.fel.dbs.lib.model.DrivesEntity;
import cvut.fel.dbs.lib.model.RouteEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DrivesService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityManager em = emf.createEntityManager();
    DrivesEntityDao dao = new DrivesEntityDao(em);

    public DrivesService(DrivesEntityDao dao) {
        this.dao = dao;
    }

    public List<DrivesEntity> findAll(){
        return dao.findAll();
    }

    public DrivesEntity find(String driver_licence_number_relText) throws Exception {
        if(driver_licence_number_relText.length() > 20){
            throw new Exception("Driver licence number length should be < 20");
        }
        if(dao.find(driver_licence_number_relText) == null){
            throw new Exception("Bus not found");
        }
        return dao.find(driver_licence_number_relText);
    }

    public void create(DrivesEntity p) throws Exception {
        if(dao.find(p.getDriver_licence_number())!=null){
            throw new Exception("This driver already have a Bus");
        }
        if(p.getCar_number().length() > 7){
            throw new Exception("Car number length should be < 7");
        }
        if(p.getDriver_licence_number().length() > 20){
            throw new Exception("Driver licence number length should be < 20");
        }
        dao.create(p);
    }

    public void delete(DrivesEntity p) throws Exception {
        if(p == null || dao.find(p.getDriver_licence_number())==null){
            throw new Exception("There is no such Entity");
        }
        dao.delete(p);
    }

    public Object[][] AlltoRows(){
        List<DrivesEntity> drivess = dao.findAll();
        Object[][] rowsDrives = new Object[drivess.size()][2];
        for (int i =0; i < drivess.size(); i++) {
            rowsDrives[i] = new Object[]{
                    drivess.get(i).getCar_number(),
                    drivess.get(i).getDriver_licence_number(),
            };
        }
        return rowsDrives;
    }

    public void merge(DrivesEntity p) throws Exception {
        if(dao.find(p.getDriver_licence_number()) == null){
            throw new Exception("Driver is not present in Driver table");
        }
        if(new BusEntityDao().find(p.getCar_number()) == null){
            throw new Exception("Bus is not present in Bus table");
        }
        em.getTransaction().begin();
        dao.merge(p);
        em.getTransaction().commit();
    }

}
