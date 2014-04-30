package org.resourcepool.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.resourcepool.core.domain.Post;
import org.resourcepool.core.domain.Tag;

import java.util.Set;
import java.util.UUID;

/**
 * Created by yoann on 18/04/14.
 */
@CacheNamespace(implementation=org.mybatis.caches.ehcache.EhcacheCache.class, readWrite = false)
public interface PostMapper {

    String SELECT_POSTS = "SELECT uuid, title, slug, created_at createdAt, content FROM post";
    
    String SELECT_POST = "SELECT uuid, title, slug, created_at createdAt, content FROM post WHERE uuid = #{uuid}";

    String INSERT_POST = "INSERT INTO post (uuid, title, slug, created_at, content, author) VALUES(" +
            "#{uuid}, #{title}, #{slug}, NOW(), #{content}, #{author.uuid})";
    
    String UPDATE_POST = "UPDATE post SET title = #{title}, slug = #{slug}, created_at = #{createdAt}, content = #{content} WHERE uuid = #{uuid}";

    String DELETE_POST = "DELETE FROM post WHERE slug = #{slug}";

    String INSERT_TAG = "REPLACE INTO post_tag VALUES (#{post.uuid}, #{tag.uuid})";

    String DELETE_TAGS = "DELETE FROM post_tag WHERE post_uuid = #{post.uuid}";

    @Select(SELECT_POSTS)
    Set<Post> getAll();
    
    @Select(SELECT_POST)
    Post get(@Param("uuid") UUID uuid);
    
    Post getBySlug(@Param("slug") String slug);

    @Insert(INSERT_POST)
    @Options(flushCache = true)
    void create(Post post);

    @Update(UPDATE_POST)
    @Options(flushCache = true)
    int save(Post post);

    @Delete(DELETE_POST)
    @Options(flushCache = true)
    int delete(@Param("slug") String slug);

    @Insert(INSERT_TAG)
    @Options(flushCache = true)
    void linkTag(@Param("post") Post post, @Param("tag") Tag tag);

    @Delete(DELETE_TAGS)
    @Options(flushCache = true)
    void deleteTags(@Param("post") Post post);

}
