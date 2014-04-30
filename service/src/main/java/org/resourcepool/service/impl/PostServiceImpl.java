package org.resourcepool.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotEmpty;
import org.resourcepool.core.domain.Post;
import org.resourcepool.persistence.mapper.PostMapper;
import org.resourcepool.persistence.mapper.TagMapper;
import org.resourcepool.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created by yoann on 19/04/14.
 */
@Service
@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    @Transactional(readOnly = true)
    public Set<Post> getAll() {
        log.debug("getAll()");
        Set<Post> posts = postMapper.getAll();
        //TODO: make a better implementation of getAll() features
        for(Post post : posts)
            post.setTags(tagMapper.getByPostUUID(post.getUuid()));
        return posts;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Post get(@NotNull UUID uuid) {
        log.debug("get({})", uuid);
        return loadTags(postMapper.get(uuid));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Post getBySlug(@NotEmpty String slug) {
        log.debug("getBySlug({})", slug);
        return loadTags(postMapper.getBySlug(slug));
    }

    @Override
    @Transactional
    public void create(@NotNull Post post) {
        log.debug("save({})", post);
        if(post.getUuid() == null)
            post.setUuid(UUID.randomUUID());
        postMapper.create(post);
        linkAllTags(post);
    }

    @Override
    @Transactional
    public boolean save(@NotNull Post post) {
        log.debug("save({})", post);
        if (0 == postMapper.save(post)) return false;
        postMapper.deleteTags(post);
        linkAllTags(post);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(@NotNull UUID uuid) {
        log.debug("delete({})", uuid);
        return postMapper.delete(uuid) > 0;
    }

    private Post loadTags(Post post) {
        if (post != null)
            post.setTags(tagMapper.getByPostUUID(post.getUuid()));
        return post;
    }

    private void linkAllTags(@NotNull Post post) {
        post.getTags().stream().forEach(t -> postMapper.linkTag(post, t));
    }
}
