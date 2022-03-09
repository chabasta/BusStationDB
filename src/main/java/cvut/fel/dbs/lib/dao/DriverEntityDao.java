package cvut.fel.dbs.lib.dao;

import cvut.fel.dbs.lib.model.BusEntity;
import cvut.fel.dbs.lib.model.DriverEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class DriverEntityDao {



    private EntityManager em;

    public DriverEntityDao() {
    }

    public DriverEntityDao(EntityManager em) {
        this.em = em;
    }

    public List<DriverEntity> findAll(){
        return em.createQuery("select b from DriverEntity b", DriverEntity.class).getResultList();
    }

    public void create(DriverEntity p){
        em.persist(p);
    }

    public DriverEntity find(Long id){
        return em.find(DriverEntity.class, id);
    }

    public DriverEntity merge(DriverEntity p){
        return em.merge(p);
    }

    public void delete(DriverEntity p){
        em.remove(p);
    }
}
