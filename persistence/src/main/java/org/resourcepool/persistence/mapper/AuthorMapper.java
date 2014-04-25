package org.resourcepool.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.resourcepool.core.domain.Author;

import java.util.Set;
import java.util.UUID;

/**
 * Created by ydemarti on 23/04/2014.
 */
@CacheNamespaceRef(PostMapper.class)
public interface AuthorMapper {

    String SELECT_AUTHORS = "SELECT * FROM author";
    
    String SELECT_AUTHOR = "SELECT * FROM author WHERE uuid = #{uuid}";

    String UPDATE_AUTHOR = "REPLACE INTO author (uuid, surname, first_name, last_name) VALUES (#{uuid}, #{surname}, #{firstName}, #{lastName})";
    
    String DELETE_AUTHOR = "DELETE FROM author WHERE uuid = #{uuid}";

    @Select(SELECT_AUTHORS)
    Set<Author> getAll();
    
    @Select(SELECT_AUTHOR)
    Author get(@Param("uuid") UUID uuid);

    @Update(UPDATE_AUTHOR)
    @Options(flushCache = true, keyProperty = "uuid")
    void save(Author author);


    @Delete(DELETE_AUTHOR)
    @Options(flushCache = true)
    void delete(UUID uuid);
}
