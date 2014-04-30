package org.resourcepool.controller.blog.fixture;

import org.resourcepool.core.domain.Author;

import java.util.UUID;

/**
 * Created by ydemarti on 30/04/2014.
 */
public class RestAuthorDataFixture {

    public static Author standardAuthor() {
        return Author.builder()
                .uuid(UUID.fromString("bbe6db4b-c7cf-11e3-93ed-e0b9a566cf57"))
                .surname("FUUUuuuuu")
                .firstName("Foo")
                .lastName("Bar")
                .build();
    }
}
