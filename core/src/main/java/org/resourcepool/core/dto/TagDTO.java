package org.resourcepool.core.dto;

import lombok.Getter;
import org.resourcepool.core.domain.Tag;

import java.util.UUID;

/**
 * Created by ydemarti on 22/04/2014.
 */
@Getter
public class TagDTO {

    private final UUID uuid;

    private String tag;

    private String slug;

    public TagDTO(Tag tag) {
        this.uuid = tag.getUuid();
        this.tag = tag.getTag();
        this.slug = tag.getSlug();
    }

    public Tag toTag() {
        return Tag.builder().uuid(uuid)
                .tag(tag)
                .slug(slug)
                .build();
    }

}
