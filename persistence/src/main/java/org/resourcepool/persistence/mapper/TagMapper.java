package org.resourcepool.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.resourcepool.core.domain.Tag;

import java.util.Set;
import java.util.UUID;

/**
 * Created by ydemarti on 22/04/2014.
 */
@CacheNamespaceRef(PostMapper.class)
public interface TagMapper {

    String SELECT_TAGS = "SELECT * FROM tag";
    
    String SELECT_TAG = "SELECT * FROM tag WHERE uuid = #{uuid}";
    
    String SELECT_TAGS_BY_POST = "SELECT t.* FROM tag t JOIN post_tag pt ON t.uuid = pt.tag_uuid WHERE pt.post_uuid = #{uuid}";

    String UPDATE_TAG = "REPLACE INTO tag (uuid, tag, slug) VALUES (#{uuid}, #{tag}, #{slug})";

    String DELETE_TAG = "DELETE FROM tag WHERE uuid = #{uuid}";

    @Select(SELECT_TAGS)
    Set<Tag> getAll();
    
    @Select(SELECT_TAG)
    Tag get(@Param("uuid") UUID uuid);
    
    @Select(SELECT_TAGS_BY_POST)
    Set<Tag> getByPostUUID(@Param("uuid") UUID uuid);

    @Update(UPDATE_TAG)
    @Options(flushCache = true)
    void save(Tag tag);

    @Delete(DELETE_TAG)
    @Options(flushCache = true)
    void delete(UUID uuid);
}
