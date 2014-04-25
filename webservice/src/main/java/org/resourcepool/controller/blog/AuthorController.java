package org.resourcepool.controller.blog;

import org.resourcepool.core.domain.Author;
import org.resourcepool.core.dto.AuthorDto;
import org.resourcepool.core.dto.PostDto;
import org.resourcepool.service.AuthorService;
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
@RequestMapping("/blog/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Set<AuthorDto>> list() {
        return new ResponseEntity<Set<AuthorDto>>(AuthorDto.fromAuthorSet(authorService.getAll()),HttpStatus.OK);
    }
    
    @ResponseBody
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<AuthorDto> show(@PathVariable("uuid") String uuid) {
        return new ResponseEntity<AuthorDto>(AuthorDto.fromAuthor(authorService.get(UUID.fromString(uuid))),HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<AuthorDto> create(AuthorDto authorDto) {
        Author author = authorDto.toAuthor();
        authorService.save(author);
        if(author.getUuid() == null)
            return new ResponseEntity<AuthorDto>(authorDto,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<AuthorDto>(AuthorDto.fromAuthor(author),HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<AuthorDto> update(AuthorDto authorDto) {
        authorService.save(authorDto.toAuthor());
        return new ResponseEntity<AuthorDto>(authorDto,HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{uuid}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable UUID uuid) {
        authorService.delete(uuid);
        return new ResponseEntity(HttpStatus.OK);
    }

}
