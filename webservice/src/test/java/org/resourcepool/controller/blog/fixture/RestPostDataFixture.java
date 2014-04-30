package org.resourcepool.controller.blog.fixture;

import org.resourcepool.core.domain.Post;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by ydemarti on 30/04/2014.
 */
public class RestPostDataFixture {

    public static final String uuid = "2d903702-50f7-4d0d-96ad-87e32aa0da95";

    public static final String slug = "boujour";

    public static Post standardPost() {
        return Post.builder()
                .uuid(UUID.fromString(uuid))
                .title("Boujour")
                .slug(slug)
                .createdAt(LocalDateTime.now())
                .content("Bonjour s'écrit en fait avec un u : Boujour !")
                .author(RestAuthorDataFixture.standardAuthor())
                .tags(RestTagDataFixture.standardTags())
                .build();
    }

    public static String standardPostJSON() {
        return"{\"uuid\":\"2d903702-50f7-4d0d-96ad-87e32aa0da9a\",\"title\":\"Boujour\",\"slug\":\"boujour2\",\"content\":\"Boujour s'écrit en fait avec un u (2ème partie).\",\"author\":{\"uuid\":\"bbe6db4b-c7cf-11e3-93ed-e0b9a566cf57\",\"surname\":\"FUUUuuuuu\",\"firstName\":\"Foo\",\"lastName\":\"Bar\"},\"tags\":[{\"uuid\":\"34fe2464-ca1f-11e3-910a-b8ca3a862977\",\"tag\":\"Java 8\",\"slug\":\"java-8\"},{\"uuid\":\"3950e28c-ca1f-11e3-910a-b8ca3a862977\",\"tag\":\"Spring\",\"slug\":\"spring\"}]}";
    }
}
