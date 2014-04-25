package org.resourcepool.controller.blog;

import org.resourcepool.core.domain.Tag;
import org.resourcepool.core.dto.TagDto;
import org.resourcepool.service.TagService;
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
@RequestMapping("/blog/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Set<TagDto>> list() {
        return new ResponseEntity<Set<TagDto>>(TagDto.fromTagSet(tagService.getAll()),HttpStatus.OK);
    }
    
    @ResponseBody
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<TagDto> get(@PathVariable("uuid") String uuid) {
        return new ResponseEntity<TagDto>(TagDto.fromTag(tagService.get(UUID.fromString(uuid))),HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/byPost/{uuid}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Set<TagDto>> getByPostUUID(@PathVariable("uuid") String uuid) {
        return new ResponseEntity<Set<TagDto>>(TagDto.fromTagSet(tagService.getByPostUUID(UUID.fromString(uuid))),HttpStatus.OK);
    }
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<TagDto> create(@RequestBody TagDto tagDto) {
        Tag tag = tagDto.toTag();
        tagService.save(tag);
        if(tag.getUuid() == null)
            return new ResponseEntity<TagDto>(tagDto,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<TagDto>(TagDto.fromTag(tag),HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<TagDto> update(@RequestBody TagDto tagDto) {
        tagService.save(tagDto.toTag());
        return new ResponseEntity<TagDto>(tagDto,HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{uuid}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable UUID uuid) {
        tagService.delete(uuid);
        return new ResponseEntity(HttpStatus.OK);
    }

}
