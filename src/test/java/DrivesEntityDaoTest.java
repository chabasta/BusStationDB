import cvut.fel.dbs.lib.dao.BusEntityDao;
import cvut.fel.dbs.lib.dao.DrivesEntityDao;
import cvut.fel.dbs.lib.model.BusEntity;
import cvut.fel.dbs.lib.model.DrivesEntity;
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
class DrivesEntityDaoTest {

    BusEntity bus = new BusEntity("123cz","666");
    BusEntity bus2 = new BusEntity("123cz2","666");
    DrivesEntity testDrives = new DrivesEntity("2020cc","123cz");

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static EntityManager em = emf.createEntityManager();
    DrivesEntityDao DrivesEntityDao = new DrivesEntityDao(em);
    BusEntityDao BusEntityDao = new BusEntityDao(em);

    @BeforeAll
    static void clearDB(){
        em.getTransaction().begin();
        em.createQuery("delete from DrivesEntity d").executeUpdate();
        em.createQuery("DELETE FROM BusEntity b").executeUpdate();
        em.getTransaction().commit();
    }

    @Test
    @Order(1)
    public void DrivesEntityDao_CreateDrives_Test(){

        // arrange
        BusEntityDao.create(bus);
        BusEntityDao.create(bus2);
        String expected = testDrives.getDriver_licence_number();

        //act
        DrivesEntityDao.create(testDrives);
        String result = DrivesEntityDao.find(testDrives.getDriver_licence_number()).getDriver_licence_number();

        //asserts
        assertEquals(expected, result);

    }


    @Test
    @Order(2)
    public void DrivesEntityDao_UpdateDrives_Test(){

        // arrange
        String expected = bus2.getCar_number();

        //act
        DrivesEntity b = DrivesEntityDao.find(testDrives.getDriver_licence_number());
        b.setCar_number(bus2.getCar_number());
        em.getTransaction().begin();
        DrivesEntityDao.merge(b);
        em.getTransaction().commit();
        String result = DrivesEntityDao.find(testDrives.getDriver_licence_number()).getCar_number();

        //asserts
        assertEquals(expected, result);

    }

    @Test
    @Order(3)
    public void DrivesEntityDao_FindDrives_Test(){

        // arrange
        String expected = testDrives.getDriver_licence_number();

        //act
        String result = DrivesEntityDao.find(testDrives.getDriver_licence_number()).getDriver_licence_number();

        //asserts
        assertEquals(expected, result);

    }

    @Test
    @Order(4)
    public void DrivesEntityDao_FindALL_Test(){

        DrivesEntity testDrives2 = new DrivesEntity("4444bb", bus.getCar_number());
        List<DrivesEntity> expDrives = Arrays.asList(testDrives, testDrives2);

        DrivesEntityDao.create(testDrives2);

        List<DrivesEntity> resultDrives = DrivesEntityDao.findAll();

        HashSet<String> resultSetNumbers = new HashSet<String>();

        for (DrivesEntity b:resultDrives
        ) {
            resultSetNumbers.add(b.getDriver_licence_number());
        }

        assertTrue(new HashSet<>(Arrays.asList(testDrives.getDriver_licence_number(), testDrives2.getDriver_licence_number()))
                .equals(resultSetNumbers));

    }

    @Test
    @Order(5)
    public void DrivesEntityDao_deleteDrives_Test(){

        // arrange
        String expected = null;
        DrivesEntity b = DrivesEntityDao.find(testDrives.getDriver_licence_number());

        //act
        if (b != null) DrivesEntityDao.delete(b);
        DrivesEntity result = DrivesEntityDao.find(testDrives.getDriver_licence_number());

        //asserts
        assertEquals(expected, result);
        

    }
    
    @AfterAll
    static void clearDB2(){
        clearDB();
    }
}
