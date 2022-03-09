import cvut.fel.dbs.lib.dao.BusEntityDao;
import cvut.fel.dbs.lib.model.BusEntity;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BusEntityDaoTest {

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
    public void BusEntityDao_CreateBus_(){
        System.out.println("Check method for create bus");
        // arrange
        String expected = testBus.getCar_number();

        //act
        busEntityDao.create(testBus);
        String result = busEntityDao.find(testBus.getCar_number()).getCar_number();

        //asserts
        assertEquals(expected, result);

    }


    @Test
    @Order(2)
    public void BusEntityDao_UpdateBus_(){

        String expected = "7";

        //act
        em.getTransaction().begin();
        BusEntity b = busEntityDao.find(testBus.getCar_number());
        b.setYears_in_use("7");
        busEntityDao.merge(b);
        em.getTransaction().commit();
        String result = busEntityDao.find(testBus.getCar_number()).getYears_in_use();

        //asserts
        assertEquals(expected, result);

    }

    @Test
    @Order(3)
    public void BusEntityDao_FindBus_(){


        String expected = testBus.getCar_number();

        //act
        String result = busEntityDao.find(testBus.getCar_number()).getCar_number();

        //asserts
        assertEquals(expected, result);

    }

    @Test
    @Order(4)
    public void BusEntityDao_FindALL_(){

        BusEntity testBus2 = new BusEntity("drdr66");

        busEntityDao.create(testBus2);

        List<BusEntity> resultBuses = busEntityDao.findAll();

        HashSet<String> resultSetNumbers = new HashSet<String>();

        for (BusEntity b:resultBuses
             ) {
            resultSetNumbers.add(b.getCar_number());
        }

        assertTrue(new HashSet<>(Arrays.asList(testBus.getCar_number(), testBus2.getCar_number()))
                .equals(resultSetNumbers));

    }

    @Test
    @Order(5)
    public void BusEntityDao_deleteBus_(){

        // arrange
        String expected = null;
        BusEntity b = busEntityDao.find(testBus.getCar_number());

        //act
        if (b != null) busEntityDao.delete(b);
        BusEntity result = busEntityDao.find(testBus.getCar_number());

        //asserts
        assertEquals(expected, result);

        clearDB();

    }

}
