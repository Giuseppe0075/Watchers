package storage.model;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.WatchBean;
import storage.WatchModel;


import java.sql.SQLException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class WatchModelTest {
    private WatchModel watchModel;

    @BeforeEach
    public void setUp() {
        watchModel = new WatchModel();
    }

    @AfterEach
    public void tearDown() {
        watchModel = null;
    }

    @Test
    public void testDoSave() {
        WatchBean watch = new WatchBean("Swatch","brand1","svefr",3.2,300.0,"kjndc",200,224.0,22,"MAN",true);
        // Set watch properties here
        try {
            watchModel.doSave(watch);
            WatchBean watchCreated = watchModel.doRetrieveByKey(1);

        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testDoSaveOrUpdate() {
        WatchBean watch = new WatchBean(4L,"Swatch","brand2","svefr",3.2,300.0,"kjndc",200,224.0,22,"MAN",true);
        // Set watch properties here
        try {
            watchModel.doSaveOrUpdate(watch);
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testDoRetrieveByKey() {
        try {
            WatchBean watch = watchModel.doRetrieveByKey(1);
            assertNotEquals(0, watch.getId());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testDoRetrieveAll() {
        try {
            Collection<WatchBean> watches = watchModel.doRetrieveAll();
            watches.forEach(System.out::println);
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testRetriveByCond(){
            try {
            Collection<WatchBean> watches = watchModel.doRetrieveByCond("brand = 'brand1'");
            watches.forEach(System.out::println);
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        } }

    @Test
    public void testDoDelete() {
        WatchBean watch = new WatchBean(4L,"tutto messo a cazzo","brand2","svefr",3.2,300.0,"kjndc",200,224.0,22,"MAN",true);
        // Set watch properties here
        try {
            watchModel.doDelete(watch);

        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
