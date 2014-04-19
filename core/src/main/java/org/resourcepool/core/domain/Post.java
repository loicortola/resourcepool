package org.resourcepool.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created by yoann on 18/04/14.
 */
@Builder
@AllArgsConstructor
@Getter @Setter
public class Post {

    private final UUID uuid;

    private String title;

    private String slug;

    private LocalDateTime createdAt;

    private String content;

    private Author author;

    private Set<Tag> tags;

    public Post() {
        this(UUID.randomUUID());
    }

    public Post(UUID uuid) {
        this.uuid = uuid;
    }

}
