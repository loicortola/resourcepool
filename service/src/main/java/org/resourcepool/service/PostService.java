package org.resourcepool.service;

import org.resourcepool.core.domain.Post;
import org.resourcepool.persistence.mapper.PostMapper;
import org.resourcepool.persistence.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by yoann on 19/04/14.
 */
@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private TagMapper tagMapper;

    @Transactional(readOnly = true)
    public Post getPostBySlug(String slug) {
        Post post = postMapper.getPostBySlug(slug);
        post.setTags(tagMapper.getTagsByPostUUID(post.getUuid()));
        return post;
    }

    @Transactional
    public void create(Post post) {
        postMapper.create(post);
        linkAllTags(post);
    }

    @Transactional
    public void update(Post post) {
        postMapper.update(post);
        postMapper.deleteTags(post);
        linkAllTags(post);
    }

    @Transactional
    public void delete(UUID uuid) {
        postMapper.delete(uuid);
    }

    private void linkAllTags(Post post) {
        post.getTags().stream().forEach(t -> postMapper.linkTag(post, t));
    }
}
