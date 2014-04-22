package org.resourcepool.controller;

import org.resourcepool.core.dto.PostDTO;
import org.resourcepool.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by yoann on 19/04/14.
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/show/{slug}", method = RequestMethod.GET)
    public PostDTO show(@PathVariable("slug") String slug) {
        return new PostDTO(postService.getPostBySlug(slug));
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public String create(PostDTO postDTO) {
        postService.create(postDTO.toPost());
        return "created";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(PostDTO postDTO) {
        postService.update(postDTO.toPost());
        return "updated";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(UUID uuid) {
        postService.delete(uuid);
        return "deleted";
    }

}
