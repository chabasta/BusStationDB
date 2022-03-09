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

public class BusService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityManager em = emf.createEntityManager();
    BusEntityDao dao = new BusEntityDao(em);
    DrivesEntityDao daoDrives = new DrivesEntityDao(em);

    public BusService(BusEntityDao dao) {
        this.dao = dao;
    }

    public cvut.fel.dbs.lib.model.BusEntity find(String car_number) throws Exception {
        if(car_number.length() > 7){
            throw new Exception("Car number length should be <= 2");
        }
        if(dao.find(car_number) == null){
            throw new Exception("Bus not found");
        }
        return dao.find(car_number);
    }

    public List<cvut.fel.dbs.lib.model.BusEntity> findAll(){
        return dao.findAll();
    }

    public void create(BusEntity p) throws Exception {
        if(p.getYears_in_use().length() > 2){
            throw new Exception("Years in use length should be <= 2");
        }
        if(p.getSize().length() > 2){
            throw new Exception("Size length should be <= 2");
        }
        if(p.getCar_number().length() > 7){
            throw new Exception("Car number length should be <= 2");
        }
        if(p.getBrand().length() > 10){
            throw new Exception("Brand length should be <= 2");
        }
        if(p.getNumber().length() > 3){
            throw new Exception("Number length should be <= 2");
        }
        if(dao.find(p.getCar_number()) != null){
            throw new Exception("This car number already present in table");
        }
        if(em.find(RouteEntity.class, p.getNumber()) == null){
            throw new Exception("Number should be present in Route table");
        }
        dao.create(p);
    }

    public void merge(BusEntity p) throws Exception {
        if(p == null || dao.find(p.getCar_number()) == null){
            throw new Exception("There is no Bus with this Car number");
        }
        if(p.getYears_in_use().length() > 2){
            throw new Exception("Years in use length should be <= 2");
        }
        if(p.getSize().length() > 2){
            throw new Exception("Size length should be <= 2");
        }
        if(p.getCar_number().length() > 7){
            throw new Exception("Car number length should be <= 2");
        }
        if(p.getBrand().length() > 10){
            throw new Exception("Brand length should be <= 2");
        }
        if(p.getNumber().length() > 3){
            throw new Exception("Number length should be <= 2");
        }
        if(em.find(RouteEntity.class, p.getNumber()) == null){
            throw new Exception("Number should be present in Route table");
        }
        em.getTransaction().begin();
        dao.merge(p);
        em.getTransaction().commit();
    }

    public void delete(BusEntity p) throws Exception {
        if(p == null || dao.find(p.getCar_number()) == null){
            throw new Exception("There is no Bus with this Car number");
        }
        for (DrivesEntity d:
             daoDrives.findAll()) {
            if(d.getCar_number()== p.getCar_number()){
                throw new Exception("Delete relation first");
            }
        }
        dao.delete(p);
    }

    public Object[][] allBusesToRows(){
        List<BusEntity> buses = dao.findAll();
        Object[][] rowsBus = new Object[buses.size()][5];
        for (int i =0; i < buses.size(); i++) {
            rowsBus[i] = new Object[]{
                    buses.get(i).getCar_number(),
                    buses.get(i).getNumber(),
                    buses.get(i).getSize(),
                    buses.get(i).getYears_in_use(),
                    buses.get(i).getBrand(),
            };
        }
        return rowsBus;
    }
}
