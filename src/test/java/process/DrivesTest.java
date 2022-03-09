package process;

import cvut.fel.dbs.lib.dao.BusEntityDao;
import cvut.fel.dbs.lib.dao.DrivesEntityDao;
import cvut.fel.dbs.lib.model.BusEntity;
import cvut.fel.dbs.lib.model.DrivesEntity;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DrivesTest {


    BusEntity testBus = new BusEntity("123test", "666", "66", "6", "Satan");
    DrivesEntity testDrive = new DrivesEntity( "2020cc", "123test");

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static EntityManager em = emf.createEntityManager();
    BusEntityDao busEntityDao = new BusEntityDao(em);
    DrivesEntityDao drivesEntityDao = new DrivesEntityDao(em);

    @BeforeAll
    static void clearDB(){
        em.getTransaction().begin();
        em.createQuery("delete from DrivesEntity d").executeUpdate();
        em.createQuery("DELETE FROM BusEntity b").executeUpdate();
        em.getTransaction().commit();
    }

    @Test
    @Order(1)
    public void create_and_delete_Drive(){
        clearDB();
        // arrange
        busEntityDao.create(testBus);
        String expected = null;

        //act
        drivesEntityDao.create(testDrive);
        assertEquals(testDrive, drivesEntityDao.find(testDrive.getDriver_licence_number()));
        drivesEntityDao.delete(testDrive);
        DrivesEntity result = drivesEntityDao.find(testDrive.getDriver_licence_number());

        //asserts
        assertEquals(expected, result);
        clearDB();
    }

    @Test
    @Order(2)
    public void create_and_update_Drive(){
        clearDB();
        //arrange
        BusEntity testBus2 = new BusEntity("555", "666", "55", "5", "Bang");
        busEntityDao.create(testBus);
        busEntityDao.create(testBus2);
        String expected = "555";

        //act
        drivesEntityDao.create(testDrive);
        testDrive.setCar_number("555");
        drivesEntityDao.merge(testDrive);
        String result = drivesEntityDao.find(testDrive.getDriver_licence_number()).getCar_number();

        //asserts
        assertEquals(expected, result);
        clearDB();
    }

    @Test
    @Order(3)
    public void create_findAll_Drives(){
        //arrange
        clearDB();

        BusEntity a = new BusEntity("1CZ");
        DrivesEntity b = new DrivesEntity("4444bb", "1CZ");
        busEntityDao.create(a);
        busEntityDao.create(testBus);

        String[] expected = {"1CZ","123test"};

        //act
        drivesEntityDao.create(b);
        drivesEntityDao.create(testDrive);
        List<DrivesEntity> list  = drivesEntityDao.findAll();
        String[] result = new String[list.size()];
        for (int i=0; i<list.size(); i++){
            result[i] = list.get(i).getCar_number();
        }

        //asserts
        for (int i=0; i<list.size(); i++){
            assertEquals(expected[i], result[i]);
        }
        clearDB();
    }


}
