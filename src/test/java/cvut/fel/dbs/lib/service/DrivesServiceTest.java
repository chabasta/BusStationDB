package cvut.fel.dbs.lib.service;

import cvut.fel.dbs.lib.dao.BusEntityDao;
import cvut.fel.dbs.lib.dao.DrivesEntityDao;
import cvut.fel.dbs.lib.model.BusEntity;
import cvut.fel.dbs.lib.model.DrivesEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DrivesServiceTest {

    private DrivesEntityDao drivesDao = Mockito.mock(DrivesEntityDao.class);
    private DrivesService drivesService;

    DrivesServiceTest(){
        MockitoAnnotations.initMocks(this);
        drivesService = new DrivesService(drivesDao);
    }

    @Test
    public void allToRows_Test(){
        //String driver_licence_number, String car_number
        Object expData[][] = {{"CZ777A", "565656cc"},{"CZ777A2", "565656c2"}};
        DrivesEntity mockdrives1 = new DrivesEntity("565656cc", "CZ777A");
        DrivesEntity mockdrives2 = new DrivesEntity("565656c2", "CZ777A2");
        List<DrivesEntity> mockList = Arrays.asList(mockdrives1,mockdrives2);
        Mockito.when(drivesDao.findAll()).thenReturn(mockList);
        Object actualData[][] = drivesService.AlltoRows();
        assertTrue(Arrays.deepEquals(expData, actualData));
    }

    @Test
    public void findByDLN_Found_Test() throws Exception {
        Mockito.when(drivesDao.find("565656cc"))
                .thenReturn(new DrivesEntity("565656cc","CZ777A"));
        assertDoesNotThrow(() -> drivesService.find("565656cc"));
        assertEquals(drivesService.find("565656cc"), new DrivesEntity("565656cc", "CZ777A"));
    }

    @Test
    public void findByDLN_NotFound_Test() throws Exception {
        Mockito.when(drivesDao.find("5666cc"))
                .thenReturn(null);
        assertThrows(Exception.class, () -> drivesService.find("5666cc"));
    }

    @Test
    public void findByDLN_InvalidDLN_Test() throws Exception {
        assertThrows(Exception.class, () -> drivesService.find("123456789999999991292129921912129219219219219"));
    }

}
