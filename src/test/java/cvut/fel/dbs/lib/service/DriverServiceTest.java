package cvut.fel.dbs.lib.service;

import cvut.fel.dbs.lib.dao.BusEntityDao;
import cvut.fel.dbs.lib.dao.DriverEntityDao;
import cvut.fel.dbs.lib.model.BusEntity;
import cvut.fel.dbs.lib.model.DriverEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverServiceTest {

    private final DriverEntityDao driverDao = Mockito.mock(DriverEntityDao.class);
    private final DriverService driverService;

    DriverServiceTest(){
        MockitoAnnotations.initMocks(this);
        driverService = new DriverService(driverDao);
    }

    @Test
    void allToRows() {
        //{"name","surname","director_name","director_surname","driver_licence_number"}
        Object[][] expData = {{"Biba","Biba","Boba","Boba","ADS123"},{"Biba2","Biba2","Boba2","Boba2","ADS1232"}};
        DriverEntity mockDriver1 = new DriverEntity("ADS123","Biba","Biba","Boba","Boba");
        DriverEntity mockDriver2 = new DriverEntity("ADS1232","Biba2","Biba2","Boba2","Boba2");
        List<DriverEntity> mockList = Arrays.asList(mockDriver1,mockDriver2);
        Mockito.when(driverDao.findAll()).thenReturn(mockList);
        Object[][] actualData = driverService.allToRows();
        assertTrue(Arrays.deepEquals(expData, actualData));
    }
}