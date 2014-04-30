package org.resourcepool.service;

import org.resourcepool.core.domain.Post;

import java.util.Set;
import java.util.UUID;

/**
 * Created by ydemarti on 30/04/2014.
 */
public interface PostService {
    Set<Post> getAll();

    Post get(UUID uuid);

    Post getBySlug(String slug);

    void create(Post post);

    boolean save(Post post);

    /**
     * Deletes the post
     * @param slug String slug of the post
     * @return true if the post existed, false otherwise
     */
    boolean delete(String slug);
}
