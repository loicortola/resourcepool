package org.resourcepool.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.resourcepool.core.domain.Author;

import java.util.Set;
import java.util.UUID;

/**
 * Created by ydemarti on 23/04/2014.
 */
public interface AuthorMapper {

    String SELECT_AUTHOR = "SELECT * FROM author WHERE uuid = #{uuid}";

    String INSERT_AUTHOR = "REPLACE INTO author (uuid, surname, first_name, last_name) VALUES (#{uuid}, #{surname}, #{firstName}, #{lastName})";

    String DELETE_AUTHOR = "DELETE FROM tag WHERE uuid = #{uuid}";

    @Select(SELECT_AUTHOR)
    Set<Author> getAuthorByUUID(@Param("uuid") UUID uuid);

    @Insert(INSERT_AUTHOR)
    @Options(flushCache = true)
    void saveOrUpdate(Author tag);

    @Delete(DELETE_AUTHOR)
    @Options(flushCache = true)
    void delete(UUID uuid);
}
