package org.resourcepool.persistence.handler;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Created by yoann on 19/04/14.
 */
public class UUIDTypeHandlerTest {

    private UUIDTypeHandler handler = new UUIDTypeHandler();

    @Test
    public void UUIDTypeHandler() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        byte[] data = handler.convert(uuid);
        UUID res = handler.convert(data);
        System.out.println(str);
        assertEquals(str, res.toString());
    }

    @Test
    public void UUIDTypeHandlerZero() {
        String zeroUUID = "00000000-0000-0000-0000-000000000000";
        byte[] data = new byte[UUIDTypeHandler.UUID_SIZE];
        UUID res = handler.convert(data);
        assertEquals(zeroUUID, res.toString());
    }
}
