package org.resourcepool.controller.blog;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.resourcepool.service.PostService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.resourcepool.controller.blog.fixture.RestPostDataFixture.*;

/**
 * Created by ydemarti on 30/04/2014.
 */
public class PostControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    private PostController controller;

    @Mock
    private PostService postService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void slugPostNotFound() throws Exception {

        when(postService.getBySlug(anyString())).thenReturn(null);

        this.mockMvc.perform(
                get("/blog/post/{slug}", slug)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void slugPostOk() throws Exception {

        when(postService.getBySlug(slug)).thenReturn(standardPost());

        this.mockMvc.perform(
                get("/blog/post/{slug}", slug)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.slug").value(slug));
    }

    @Test
    public void uuidPostNotFound() throws Exception {

        when(postService.get(any(UUID.class))).thenReturn(null);

        this.mockMvc.perform(
                get("/blog/post/uuid/{uuid}", uuid)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void uuidPostOk() throws Exception {

        when(postService.get(UUID.fromString(uuid))).thenReturn(standardPost());

        this.mockMvc.perform(
                get("/blog/post/uuid/{uuid}", uuid)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.uuid").value(uuid));
    }

    @Test
    public void deletePostOkOnSuccess() throws Exception {

        when(postService.delete(anyString()))
                .thenReturn(true);

        this.mockMvc.perform(
                delete("/blog/post/{slug}", slug)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(postService).delete((argThat(Matchers.equalTo(slug))));
    }

    @Test
    public void deletePostNotFoundOnEntityLookupFailure() throws Exception {

        when(postService.delete(anyString()))
                .thenReturn(false);

        this.mockMvc.perform(
                delete("/blog/post/{slug}", slug)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(postService).delete((argThat(Matchers.equalTo(slug))));

    }

    @Test
    public void createPostCreated() throws Exception {

        this.mockMvc.perform(
                post("/blog/post")
                        .content(standardPostJSON())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
