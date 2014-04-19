package org.resourcepool.core.dto;

import lombok.Getter;
import org.resourcepool.core.domain.Post;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by ydemarti on 22/04/2014.
 */
@Getter
public class PostDTO {

    private final UUID uuid;

    private String title;

    private String slug;

    private String createdAt;

    private String content;

    private AuthorDTO author;

    private Set<TagDTO> tags;

    public PostDTO(Post post) {
        uuid = post.getUuid();
        title = post.getTitle();
        slug = post.getSlug();
        createdAt = post.getCreatedAt().toString();
        content = post.getContent();
        author = new AuthorDTO(post.getAuthor());
        tags = post.getTags().stream().map(TagDTO::new).collect(Collectors.toSet());
    }

    public Post toPost() {
        return Post.builder().uuid(uuid)
                .title(title)
                .slug(slug)
                .createdAt(LocalDateTime.parse(createdAt))
                .content(content)
                .author(author.toAuthor())
                .tags(tags.stream().map(TagDTO::toTag).collect(Collectors.toSet()))
                .build();
    }

}
