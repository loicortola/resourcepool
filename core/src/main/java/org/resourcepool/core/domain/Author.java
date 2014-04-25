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
public class Author implements Serializable {

    private UUID uuid;

    private String surname;

    private String firstName;

    private String lastName;

    public Author() {
        this(UUID.randomUUID());
    }

    public Author(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
