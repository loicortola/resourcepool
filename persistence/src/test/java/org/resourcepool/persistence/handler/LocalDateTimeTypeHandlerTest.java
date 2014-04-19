package org.resourcepool.persistence.handler;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Created by yoann on 19/04/14.
 */
public class LocalDateTimeTypeHandlerTest {

    private LocalDateTimeTypeHandler handler = new LocalDateTimeTypeHandler();

    @Test
    public void LocalDateTimeTypeHandler() {
        LocalDateTime dateTime = LocalDateTime.now();
        Timestamp timestamp = handler.convert(dateTime);
        LocalDateTime res = handler.convert(timestamp);
        assertEquals(res, dateTime);
    }

    @Test
    public void LocalDateTimeTypeHandler14() {
        LocalDateTime dateTime = LocalDateTime.of(2014, 4, 14, 14, 14, 14);
        Timestamp timestamp = handler.convert(dateTime);
        LocalDateTime res = handler.convert(timestamp);
        assertEquals(res, dateTime);
    }
}
