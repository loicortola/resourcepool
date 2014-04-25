package org.resourcepool.service;

import lombok.extern.slf4j.Slf4j;
import org.resourcepool.core.domain.Tag;
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
public class TagService {

    @Autowired
    private TagMapper tagMapper;

    @Transactional(readOnly = true)
    public Set<Tag> getAll() {
        log.debug("getAll()");
        return tagMapper.getAll();
    }
    
    @Transactional(readOnly = true)
    public Tag get(@NotNull UUID uuid) {
        log.debug("get({})", uuid);
        return tagMapper.get(uuid);
    }
    
    @Transactional(readOnly = true)
    public Set<Tag> getByPostUUID(@NotNull UUID uuid) {
        log.debug("getByPostUUID({})", uuid);
        return tagMapper.getByPostUUID(uuid);
    }
    
    @Transactional
    public void save(@NotNull Tag tag) {
        log.debug("save({})", tag);
        if(tag.getUuid() == null)
            tag.setUuid(UUID.randomUUID());
            
        tagMapper.save(tag);
    }

    @Transactional
    public void delete(@NotNull UUID uuid) {
        log.debug("delete({})", uuid);
        tagMapper.delete(uuid);
    }
}
