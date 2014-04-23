package org.resourcepool.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by yoann on 18/04/14.
 */
@Builder
@AllArgsConstructor
@Getter @Setter
public class Tag implements Serializable {

    private final UUID uuid;

    private String tag;

    private String slug;

    public Tag() {
        this(UUID.randomUUID());
    }

    public Tag(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return tag;
    }

}
