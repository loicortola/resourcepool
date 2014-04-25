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
public class PostDto {

    private final UUID uuid;

    private String title;

    private String slug;

    private String createdAt;

    private String content;

    private AuthorDto author;

    private Set<TagDto> tags;

    public PostDto() {
        uuid = null;
    }
    
    private PostDto(Post post) {
        uuid = post.getUuid();
        title = post.getTitle();
        slug = post.getSlug();
        createdAt = post.getCreatedAt().toString();
        content = post.getContent();
        author = AuthorDto.fromAuthor(post.getAuthor());
        tags = TagDto.fromTagSet(post.getTags());
    }

    public Post toPost() {
        return Post.builder().uuid(uuid)
                .title(title)
                .slug(slug)
                .createdAt(LocalDateTime.parse(createdAt))
                .content(content)
                .author(author.toAuthor())
                .tags(tags.stream().map(TagDto::toTag).collect(Collectors.toSet()))
                .build();
    }
    
    public static PostDto fromPost(Post post) {
        return new PostDto(post);
    }

    public static Set<PostDto> fromPostSet(Set<Post> posts) {
        return posts.stream().map(PostDto::fromPost).collect(Collectors.toSet());
    }
}
