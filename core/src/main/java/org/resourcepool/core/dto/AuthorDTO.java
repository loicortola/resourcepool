package org.resourcepool.core.dto;

import lombok.Getter;
import org.resourcepool.core.domain.Author;

import java.util.UUID;

/**
 * Created by ydemarti on 22/04/2014.
 */
@Getter
public class AuthorDTO {

    private final UUID uuid;

    private String surname;

    private String firstName;

    private String lastName;

    public AuthorDTO(Author author) {
        this.uuid = author.getUuid();
        this.surname = author.getSurname();
        this.firstName = author.getFirstName();
        this.lastName = author.getLastName();
    }

    public Author toAuthor() {
        return Author.builder().uuid(uuid)
                .surname(surname)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

}
