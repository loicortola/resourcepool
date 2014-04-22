package org.resourcepool.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.resourcepool.core.domain.Post;
import org.resourcepool.core.domain.Tag;

import java.util.UUID;

/**
 * Created by yoann on 18/04/14.
 */
public interface PostMapper {

    String INSERT_POST = "INSERT INTO post (uuid, title, slug, created_at, content, author) VALUES(" +
            "#{uuid}, #{title}, #{slug}, #{createdAt}, #{content}, #{author.uuid})";

    String UPDATE_POST = "UPDATE post SET title = #{title}, slug = #{slug}, content = #{content} WHERE uuid = #{uuid}";

    String DELETE_POST = "DELETE FROM post WHERE uuid = #{uuid}";

    String INSERT_TAG = "REPLACE INTO post_tag VALUES (#{post.uuid}, #{tag.uuid})";

    String DELETE_TAGS = "DELETE FROM post_tag WHERE post_uuid = #{post.uuid}";

    Post getPostBySlug(String slug);

    @Insert(INSERT_POST)
    @Options(flushCache = true)
    void create(Post post);

    @Update(UPDATE_POST)
    @Options(flushCache = true)
    void update(Post post);

    @Delete(DELETE_POST)
    @Options(flushCache = true)
    void delete(@Param("uuid") UUID uuid);

    @Insert(INSERT_TAG)
    @Options(flushCache = true)
    void linkTag(@Param("post") Post post, @Param("tag") Tag tag);

    @Delete(DELETE_TAGS)
    @Options(flushCache = true)
    void deleteTags(@Param("post") Post post);

}
