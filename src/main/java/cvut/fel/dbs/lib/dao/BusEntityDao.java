package cvut.fel.dbs.lib.dao;

import cvut.fel.dbs.lib.model.BusEntity;

import javax.persistence.EntityManager;
import java.util.List;

public class BusEntityDao {



    private EntityManager em;



    public BusEntityDao() {
    }

    public BusEntityDao(EntityManager em) {
        this.em = em;
    }

    public List<cvut.fel.dbs.lib.model.BusEntity> findAll(){
        return em.createQuery("select b from BusEntity b", cvut.fel.dbs.lib.model.BusEntity.class).getResultList();
    }

    public void create(cvut.fel.dbs.lib.model.BusEntity p){
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public cvut.fel.dbs.lib.model.BusEntity find(String car_number){
        return em.find(cvut.fel.dbs.lib.model.BusEntity.class, car_number);
    }

    public cvut.fel.dbs.lib.model.BusEntity merge(cvut.fel.dbs.lib.model.BusEntity p){
        return em.merge(p);
    }

    public void delete(cvut.fel.dbs.lib.model.BusEntity p){
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
    }
}
