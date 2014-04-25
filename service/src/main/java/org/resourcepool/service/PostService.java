package org.resourcepool.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotEmpty;
import org.resourcepool.core.domain.Post;
import org.resourcepool.persistence.mapper.PostMapper;
import org.resourcepool.persistence.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

/**
 * Created by yoann on 19/04/14.
 */
@Service
@Slf4j
public class PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private TagMapper tagMapper;

    @Transactional(readOnly = true)
    public Set<Post> getAll() {
        log.debug("getAll()");
        Set<Post> posts = postMapper.getAll();
        //TODO: make a better implementation of getAll() features
        for(Post post : posts)
            post.setTags(tagMapper.getByPostUUID(post.getUuid()));
        return posts;
    }
    
    @Transactional(readOnly = true)
    public Post get(@NotNull UUID uuid) {
        log.debug("get({})", uuid);
        Post post = postMapper.get(uuid);
        post.setTags(tagMapper.getByPostUUID(post.getUuid()));
        return post;
    }
    
    @Transactional(readOnly = true)
    public Post getBySlug(@NotEmpty String slug) {
        log.debug("getBySlug({})", slug);
        Post post = postMapper.getBySlug(slug);
        post.setTags(tagMapper.getByPostUUID(post.getUuid()));
        return post;
    }

    @Transactional
    public void save(@NotNull Post post) {
        log.debug("save({})", post);
        if(post.getUuid() == null)
            post.setUuid(UUID.randomUUID());
        
        postMapper.save(post);
        postMapper.deleteTags(post);
        linkAllTags(post);    
    }

    @Transactional
    public void delete(@NotNull UUID uuid) {
        log.debug("delete({})", uuid);
        postMapper.delete(uuid);
    }

    private void linkAllTags(@NotNull Post post) {
        post.getTags().stream().forEach(t -> postMapper.linkTag(post, t));
    }
}
