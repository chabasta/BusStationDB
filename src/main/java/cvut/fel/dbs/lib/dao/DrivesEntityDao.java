package cvut.fel.dbs.lib.dao;

import cvut.fel.dbs.lib.model.BusEntity;
import cvut.fel.dbs.lib.model.DrivesEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class DrivesEntityDao {



    private EntityManager em;

    public DrivesEntityDao() {
    }

    public DrivesEntityDao(EntityManager em) {
        this.em = em;
    }

    public List<DrivesEntity> findAll(){
        return em.createQuery("select b from DrivesEntity b", DrivesEntity.class).getResultList();
    }

    public void create(DrivesEntity p){
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public DrivesEntity find(String driver_licence_number_relText){
        return em.find(DrivesEntity.class,driver_licence_number_relText);
    }

    public DrivesEntity merge(DrivesEntity p){
        return em.merge(p);
    }

    public void delete(DrivesEntity p){
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
    }
}
