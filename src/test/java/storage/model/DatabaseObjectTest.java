package storage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.AdminBean;
import storage.WatchBean;
import utils.ConfigurationProperties;

import java.util.List;

class DatabaseObjectTest {

    @BeforeEach
    void setUp() {
        String url = ConfigurationProperties.getUrl();
        System.out.println(url);
    }


    @Test
    void retriveAll() throws Exception {

        List<WatchBean> watches = WatchBean.retriveAll(WatchBean.class);
        System.out.println("Watches: ");
        watches.stream().map(WatchBean::toJson).forEach(System.out::println);

        AdminBean admin = AdminBean.retriveById(AdminBean.class, 1L);
        System.out.println("Admin: ");
        System.out.println(admin);




        Thread.sleep(1000);
    }
}