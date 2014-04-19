package org.resourcepool.service;

import org.resourcepool.core.domain.Post;
import org.resourcepool.persistence.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yoann on 19/04/14.
 */
@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    public void create(Post post) {
        postMapper.create(post);
    }

    public Post getPostBySlug(String slug) {
        Post post = postMapper.getPostBySlug(slug);
        // post.getTags();
        return post;
    }
}
