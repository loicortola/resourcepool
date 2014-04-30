package org.resourcepool.controller.blog;

import org.resourcepool.Application;
import org.resourcepool.core.domain.Post;
import org.resourcepool.core.dto.PostDto;
import org.resourcepool.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

/**
 * Created by yoann on 19/04/14.
 */
@RestController
@RequestMapping("/blog/post")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(method = RequestMethod.GET, produces = Application.JSON_UTF_8)
    public ResponseEntity<Set<PostDto>> list() {
        return new ResponseEntity<Set<PostDto>>(PostDto.fromPostSet(postService.getAll()),HttpStatus.OK);
    }

    @RequestMapping(value = "/uuid/{uuid}", method = RequestMethod.GET, produces = Application.JSON_UTF_8)
    public ResponseEntity<PostDto> get(@PathVariable("uuid") String uuid) {
        return toResponseEntityPostDto(postService.get(UUID.fromString(uuid)));
    }

    @RequestMapping(value = "/{slug}", method = RequestMethod.GET, produces = Application.JSON_UTF_8)
    public ResponseEntity<PostDto> getBySlug(@PathVariable("slug") String slug) {
        return toResponseEntityPostDto(postService.getBySlug(slug));
    }

    @RequestMapping(method = RequestMethod.POST, produces = Application.JSON_UTF_8)
    public ResponseEntity create(@RequestBody PostDto postDto) {
        Post post = postDto.toPost();
        postService.create(post);
        return new ResponseEntity(post.getUuid() == null ? HttpStatus.NOT_ACCEPTABLE : HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{slug}", method = RequestMethod.PUT, produces = Application.JSON_UTF_8)
    public ResponseEntity update(PostDto postDto) {
        if (postDto.getUuid() == null)
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity(postService.save(postDto.toPost()) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.DELETE, produces = Application.JSON_UTF_8)
    public ResponseEntity delete(@PathVariable UUID uuid) {
        return new ResponseEntity(postService.delete(uuid) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<PostDto> toResponseEntityPostDto(Post post) {
        if (post == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(PostDto.fromPost(post), HttpStatus.OK);
    }

}
