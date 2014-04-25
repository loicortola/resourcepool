package org.resourcepool.core.dto;

import lombok.Getter;
import org.resourcepool.core.domain.Tag;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by ydemarti on 22/04/2014.
 */
@Getter
public class TagDto {

    private final UUID uuid;

    private String tag;

    private String slug;

    public TagDto() {
        uuid = null;
    }
    
    private TagDto(Tag tag) {
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

    public static TagDto fromTag(Tag tag) {
        return new TagDto(tag);
    }
    
    public static Set<TagDto> fromTagSet(Set<Tag> tags) {
        return tags.stream().map(TagDto::fromTag).collect(Collectors.toSet());
    }
}
