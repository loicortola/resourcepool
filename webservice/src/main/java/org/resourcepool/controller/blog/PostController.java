package org.resourcepool.controller.blog;

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

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Set<PostDto>> list() {
        return new ResponseEntity<Set<PostDto>>(PostDto.fromPostSet(postService.getAll()),HttpStatus.OK);
    }
    
    @ResponseBody
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PostDto> get(@PathVariable("uuid") String uuid) {
        return new ResponseEntity<PostDto>(PostDto.fromPost(postService.get(UUID.fromString(uuid))),HttpStatus.OK);
    }


    @ResponseBody
    @RequestMapping(value = "/bySlug/{slug}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PostDto> getBySlug(@PathVariable("slug") String slug) {
        return new ResponseEntity<PostDto>(PostDto.fromPost(postService.getBySlug(slug)),HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<PostDto> create(PostDto postDto) {
        Post post = postDto.toPost();
        postService.save(post);
        if(post.getUuid() == null)
            return new ResponseEntity<PostDto>(postDto,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<PostDto>(PostDto.fromPost(post),HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<PostDto> update(PostDto postDto) {
        postService.save(postDto.toPost());
        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{uuid}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable UUID uuid) {
        postService.delete(uuid);
        return new ResponseEntity(HttpStatus.OK);
    }

}
