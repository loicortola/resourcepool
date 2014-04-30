package org.resourcepool.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.resourcepool.core.domain.Tag;
import org.resourcepool.persistence.mapper.TagMapper;
import org.resourcepool.service.TagService;
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
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    @Transactional(readOnly = true)
    public Set<Tag> getAll() {
        log.debug("getAll()");
        return tagMapper.getAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Tag get(@NotNull UUID uuid) {
        log.debug("get({})", uuid);
        return tagMapper.get(uuid);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Set<Tag> getByPostUUID(@NotNull UUID uuid) {
        log.debug("getByPostUUID({})", uuid);
        return tagMapper.getByPostUUID(uuid);
    }
    
    @Override
    @Transactional
    public void save(@NotNull Tag tag) {
        log.debug("save({})", tag);
        if(tag.getUuid() == null)
            tag.setUuid(UUID.randomUUID());
            
        tagMapper.save(tag);
    }

    @Override
    @Transactional
    public void delete(@NotNull UUID uuid) {
        log.debug("delete({})", uuid);
        tagMapper.delete(uuid);
    }
}
