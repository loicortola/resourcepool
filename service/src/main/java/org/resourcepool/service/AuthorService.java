package org.resourcepool.service;

import lombok.extern.slf4j.Slf4j;
import org.resourcepool.core.domain.Author;
import org.resourcepool.persistence.mapper.AuthorMapper;
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
public class AuthorService {

    @Autowired
    private AuthorMapper authorMapper;

    @Transactional(readOnly = true)
    public Set<Author> getAll() {
        log.debug("getAll()");
        return authorMapper.getAll();
    }
    
    @Transactional(readOnly = true)
    public Author get(@NotNull UUID uuid) {
        log.debug("get({})", uuid);
        return authorMapper.get(uuid);
    }
    
    @Transactional
    public void save(@NotNull Author author) {
        log.debug("save({})", author);
        if(author.getUuid() == null)
            author.setUuid(UUID.randomUUID());
        
        authorMapper.save(author);    
        
    }

    @Transactional
    public void delete(@NotNull UUID uuid) {
        log.debug("delete({})", uuid);
        authorMapper.delete(uuid);
    }
}
