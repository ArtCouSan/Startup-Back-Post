package br.com.posts.endpoint.controller;

import br.com.posts.endpoint.dto.PostAlterDTO;
import br.com.posts.endpoint.dto.PostSaveDTO;
import br.com.posts.endpoint.entity.Post;
import br.com.posts.endpoint.enums.PostStatusEnum;
import br.com.posts.endpoint.service.PostService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {

    @Mock
    PostService postService;

    @InjectMocks
    private PostsController postsController;

    @Test
    public void savePostTest() {
        PostSaveDTO postSaveDTO = createSavePostDTO();
        Post post = createPost();

        Mockito
                .when(this.postService.savePost(postSaveDTO))
                .thenReturn(post);

        ResponseEntity responseEntity = this.postsController.savePost(postSaveDTO);
        Assert.assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        Assert.assertEquals(post,responseEntity.getBody());
    }

    @Test
    public void alterPostTest() {
        PostAlterDTO postAlterDTO = createAlterPostDTO();
        Post postAlter = createPost();

        postAlter.setTitle("Test alter title");
        postAlter.setDescription("Test alter description");
        postAlter.setDateAlter(LocalDateTime.now());

        Mockito
                .when(this.postService.alterPost(1l, postAlterDTO))
                .thenReturn(postAlter);

        ResponseEntity responseEntity = this.postsController.alterPost(1l, postAlterDTO);
        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assert.assertEquals(postAlter,responseEntity.getBody());
    }

    @Test
    public void deletePostTest() {
        Optional<Post> postFinded = Optional.of(createPost());
        Post postDeleted = createPost();

        postDeleted.setStatus(PostStatusEnum.DISABLE);
        postDeleted.setDateDelete(LocalDateTime.now());

        Mockito
                .when(this.postService.deletePost(1l))
                .thenReturn(postDeleted);

        ResponseEntity responseEntity = this.postsController.deletePost(1l);
        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assert.assertEquals(postDeleted,responseEntity.getBody());
    }

    @Test
    public void findPostTest() {
        Post postFinded = createPost();
        Mockito
                .when(this.postService.findPost(1l))
                .thenReturn(postFinded);

        ResponseEntity responseEntity = this.postsController.findPost(1l);
        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assert.assertEquals(postFinded,responseEntity.getBody());
    }

    @Test
    public void listPostTest() {
        List<Post> postList = new ArrayList<>();
        postList.add(createPost());

        Mockito
                .when(this.postService.listPost())
                .thenReturn(postList);

        ResponseEntity responseEntity = this.postsController.listPost();
        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assert.assertEquals(postList,responseEntity.getBody());
    }

    private Post createPost() {
        Post post = new Post();
        post.setId(1l);
        post.setStatus(PostStatusEnum.ACTIVE);
        post.setTitle("Test title");
        post.setDescription("Test description");
        post.setDateInsert(LocalDateTime.now());
        return post;
    }

    private PostSaveDTO createSavePostDTO() {
        PostSaveDTO post = new PostSaveDTO();
        post.setTitle("Test title");
        post.setDescription("Test description");
        return post;
    }

    private PostAlterDTO createAlterPostDTO() {
        PostAlterDTO post = new PostAlterDTO();
        post.setTitle("Test alter title");
        post.setDescription("Test alter Description");
        return post;
    }

}
