package org.resourcepool.service;

import org.resourcepool.core.domain.Author;

import java.util.Set;
import java.util.UUID;

/**
 * Created by ydemarti on 30/04/2014.
 */
public interface AuthorService {
    Set<Author> getAll();

    Author get(UUID uuid);

    void save(Author author);

    void delete(UUID uuid);
}
