package storage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.AdminBeen;
import storage.WatchBeen;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseObjectTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void retriveAll() throws Exception {
        List<WatchBeen> watches = WatchBeen.retriveAll(WatchBeen.class);
        System.out.println("Watches: ");
        watches.forEach(System.out::println);

        AdminBeen admin = AdminBeen.retriveById(AdminBeen.class, BigInteger.valueOf(1));
        System.out.println("Admin: ");
        System.out.println(admin);
        Thread.sleep(1000);
    }
}