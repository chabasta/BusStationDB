package process;

import cvut.fel.dbs.lib.dao.BusEntityDao;
import cvut.fel.dbs.lib.model.BusEntity;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BusTest {


    BusEntity testBus = new BusEntity("123test", "666", "66", "6", "Satan");

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static EntityManager em = emf.createEntityManager();
    BusEntityDao busEntityDao = new BusEntityDao(em);

    @BeforeAll
    static void clearDB(){
        em.getTransaction().begin();
        em.createQuery("delete from DrivesEntity d").executeUpdate();
        em.createQuery("DELETE FROM BusEntity b").executeUpdate();
        em.getTransaction().commit();
    }

    @Test
    @Order(1)
    public void create_and_delete_Bus(){
        // arrange
        String expected = null;

        //act
        busEntityDao.create(testBus);
        assertEquals(testBus, busEntityDao.find(testBus.getCar_number()));
        busEntityDao.delete(testBus);
        BusEntity result = busEntityDao.find(testBus.getCar_number());

        //asserts
        assertEquals(expected, result);
    }

    @Test
    @Order(2)
    public void create_and_upade_Bus(){
        //arrange
        String expected = "Bufg";

        //act
        busEntityDao.create(testBus);
        assertEquals(testBus.getBrand(), busEntityDao.find(testBus.getCar_number()).getBrand());
        testBus.setBrand("Bufg");
        busEntityDao.merge(testBus);
        String result = busEntityDao.find(testBus.getCar_number()).getBrand();

        //asserts
        assertEquals(expected, result);
    }

    @Test
    @Order(3)
    public void delete_findAll_Bus(){
        //arrange
        clearDB();
        for (int i = 0; i<5; i++){
            BusEntity p = new BusEntity(i+"CZ");
            busEntityDao.create(p);
        }

        String[] expected = {"0CZ","1CZ","2CZ","4CZ"};


        //act
        busEntityDao.delete(busEntityDao.find("3CZ"));
        List<cvut.fel.dbs.lib.model.BusEntity> list  = busEntityDao.findAll();
        String[] result = new String[list.size()];
        for (int i=0; i<list.size(); i++){
            result[i] = list.get(i).getCar_number();
        }

        //asserts
        for (int i=0; i<list.size(); i++){
            assertEquals(expected[i], result[i]);
        }
    }


}
