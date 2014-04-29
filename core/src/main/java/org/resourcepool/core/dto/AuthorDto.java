package org.resourcepool.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.resourcepool.core.domain.Author;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by ydemarti on 22/04/2014.
 */
@Getter
public class AuthorDto {

    private final UUID uuid;

    private String surname;

    private String firstName;

    private String lastName;

    private AuthorDto() {
        uuid = null;
    }
    
    private AuthorDto(Author author) {
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
    
    public static AuthorDto fromAuthor(Author author) {
        return new AuthorDto(author);
    }

    public static Set<AuthorDto> fromAuthorSet(Set<Author> authors) {
        return authors.stream().map(AuthorDto::fromAuthor).collect(Collectors.toSet());
    }
}
