package org.resourcepool.persistence.mapper;

import org.apache.ibatis.annotations.*;
import org.resourcepool.core.domain.Post;

import java.util.UUID;

/**
 * Created by yoann on 18/04/14.
 */
public interface PostMapper {

    String INSERT_POST = "INSERT INTO post (uuid, title, slug, created_at, content, author) VALUES(" +
            "#{uuid}, #{title}, #{slug}, #{createdAt}, #{content}, #{author.uuid})";

    String UPDATE_POST = "UPDATE post SET title = #{title}, slug = #{slug}, content = #{content} WHERE uuid = #{uuid}";

    String DELETE_POST = "DELETE FROM post WHERE uuid = #{uuid}";

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

}
