package cvut.fel.dbs.lib.service;

import cvut.fel.dbs.lib.dao.BusEntityDao;
import cvut.fel.dbs.lib.model.BusEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BusServiceTest {

    private BusEntityDao busDao = Mockito.mock(BusEntityDao.class);
    private BusService busService;

    BusServiceTest(){
        MockitoAnnotations.initMocks(this);
        busService = new BusService(busDao);
    }
    
    @Test
    public void allBusesToRows_Test(){
        //{"car_number","number","size","years_in_use","brand"}
        Object expData[][] = {{"123cz","666","24","7","LADA"},{"321cz","666","25","8","ADAL"}};
        BusEntity mockBus1 = new BusEntity("123cz","666","24","7","LADA");
        BusEntity mockBus2 = new BusEntity("321cz","666","25","8","ADAL");
        List<BusEntity> mockList = Arrays.asList(mockBus1,mockBus2);
        Mockito.when(busDao.findAll()).thenReturn(mockList);
        Object actualData[][] = busService.allBusesToRows();
        assertTrue(Arrays.deepEquals(expData, actualData));
    }

    @Test
    public void findByCarNumber_Found_Test() throws Exception {
        Mockito.when(busDao.find("123cz"))
                .thenReturn(new BusEntity("123cz","666","24","7","LADA"));
        assertDoesNotThrow(() -> busService.find("123cz"));
        assertEquals(busService.find("123cz"), new BusEntity("123cz","666","24","7","LADA"));
    }

    @Test
    public void findByCarNumber_NotFound_Test() throws Exception {
        Mockito.when(busDao.find("123cz"))
                .thenReturn(null);
        assertThrows(Exception.class, () -> busService.find("123cz"));
    }

    @Test
    public void findByCarNumber_InvalidNumber_Test() throws Exception {
        assertThrows(Exception.class, () -> busService.find("12345678"));
    }
}