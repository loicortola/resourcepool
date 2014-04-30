package org.resourcepool.service;

import org.resourcepool.core.domain.Tag;

import java.util.Set;
import java.util.UUID;

/**
 * Created by ydemarti on 30/04/2014.
 */
public interface TagService {
    Set<Tag> getAll();

    Tag get(UUID uuid);

    Set<Tag> getByPostUUID(UUID uuid);

    void save(Tag tag);

    void delete(UUID uuid);
}
