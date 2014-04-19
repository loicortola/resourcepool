package org.resourcepool.controller;

import org.resourcepool.core.domain.Author;
import org.resourcepool.core.domain.Post;
import org.resourcepool.persistence.mapper.PostMapper;
import org.resourcepool.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by yoann on 19/04/14.
 */
@RestController
public class BlogController {

    @Autowired
    private PostService postService;

    @RequestMapping("/blog/show/{slug}")
    public Post list(@PathVariable("slug") String slug) {
        return postService.getPostBySlug(slug);
    }

    @RequestMapping("/blog/create")
    public String create() {
        Post p = new Post();
        p.setTitle("Boujour");
        p.setSlug("boujour");
        p.setContent("Boujour s'écrit en fait avec un u.");
        p.setCreatedAt(LocalDateTime.now());
        Author a = new Author(UUID.fromString("bbe6db4b-c7cf-11e3-93ed-e0b9a566cf57"));
        p.setAuthor(a);
        postService.create(p);
        return "created";
    }
}
