package org.resourcepool.controller.blog.fixture;


import org.resourcepool.core.domain.Tag;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by ydemarti on 30/04/2014.
 */
public class RestTagDataFixture {

    public static Set<Tag> standardTags() {
        Set<Tag> res = new HashSet<>();
        res.add(standardTag());
        res.add(standardTag2());
        return res;
    }

    public static Tag standardTag() {
        return Tag.builder()
                .uuid(UUID.fromString("34fe2464-ca1f-11e3-910a-b8ca3a862977"))
                .tag("Java 8")
                .slug("java-8")
                .build();
    }

    public static Tag standardTag2() {
        return Tag.builder()
                .uuid(UUID.fromString("3950e28c-ca1f-11e3-910a-b8ca3a862977"))
                .tag("Spring")
                .slug("spring")
                .build();
    }
}
