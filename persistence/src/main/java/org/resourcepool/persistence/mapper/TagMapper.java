package org.resourcepool.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.resourcepool.core.domain.Tag;

import java.util.Set;
import java.util.UUID;

/**
 * Created by ydemarti on 22/04/2014.
 */
public interface TagMapper {

    String SELECT_TAGS_BY_POST = "SELECT t.* FROM tag t JOIN post_tag pt ON t.uuid = pt.tag_uuid WHERE pt.post_uuid = #{uuid}";

    String INSERT_TAG = "REPLACE INTO tag (uuid, tag, slug) VALUES (#{uuid}, #{tag}, #{slug})";

    String DELETE_TAG = "DELETE FROM tag WHERE uuid = #{uuid}";

    @Select(SELECT_TAGS_BY_POST)
    Set<Tag> getTagsByPostUUID(@Param("uuid") UUID uuid);

    @Insert(INSERT_TAG)
    @Options(flushCache = true)
    void saveOrUpdate(Tag tag);

    @Delete(DELETE_TAG)
    @Options(flushCache = true)
    void delete(UUID uuid);
}
