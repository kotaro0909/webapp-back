package com.example.backed.libs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
class DateUtilsTest {
    Logger logger = Logger.getLogger(DateUtilsTest.class.getName());

    @Test
    void getNow() {
        ActiveProfile p = AppProfile.getInstance().getProfile();
        Date n = DateUtils.getNow();
        //System.out.println(n);
        logger.info(n.toString());
        logger.info(AppProfile.getInstance().getEnv("spring.sql.init.data-locations"));
    }

    @Test
    void toDate() throws Exception {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date expected1 = format1.parse("2019/1/2 2:3:4");
        assertEquals(expected1, DateUtils.toDate("2019/01/02 02:03:04"));
        assertEquals(expected1, DateUtils.toDate("2019-01-02 02:03:04"));
        assertEquals(expected1, DateUtils.toDate("2019/1/2 2:03:04"));
        assertEquals(expected1, DateUtils.toDate("2019-1-2 2:03:04"));

        SimpleDateFormat format2 = new SimpleDateFormat("yyyy/MM/dd");
        Date expected2 = format2.parse("2019/1/2");
        assertEquals(expected2, DateUtils.toDate("2019/01/02"));
        assertEquals(expected2, DateUtils.toDate("2019-01-02"));
        assertEquals(expected2, DateUtils.toDate("2019/1/2"));
        assertEquals(expected2, DateUtils.toDate("2019-1-2"));
    }

    @Test
    void same() {
        Date val1 = DateUtils.toDate("2019/12/11");
        Date val2 = DateUtils.toDate("2019/12/11");
        assertEquals(true, DateUtils.same(val1, val2));
    }
}