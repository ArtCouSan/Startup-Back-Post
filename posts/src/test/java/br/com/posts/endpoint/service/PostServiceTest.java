package br.com.posts.endpoint.service;

import br.com.posts.endpoint.dto.PostAlterDTO;
import br.com.posts.endpoint.dto.PostSaveDTO;
import br.com.posts.endpoint.entity.Post;
import br.com.posts.endpoint.enums.PostStatusEnum;
import br.com.posts.endpoint.repository.PostRepository;
import br.com.posts.endpoint.service.impl.PostServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @Mock
    PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void savePostTest() {
        PostSaveDTO postSaveDTO = createSavePostDTO();
        Post post = createPost();
        Mockito.when(this.postRepository.save(Mockito.any())).thenReturn(post);
        Post postCreated = this.postService.savePost(postSaveDTO);
        Assert.assertNotNull(postCreated);
    }

    @Test
    public void deletePostTest() {
        Optional<Post> postFinded = Optional.of(createPost());
        Post post = postFinded.get();
        post.setStatus(PostStatusEnum.DISABLE);
        post.setDateDelete(LocalDateTime.now());
        Mockito.when(this.postRepository.findById(1l)).thenReturn(postFinded);
        Mockito.when(this.postRepository.save(post)).thenReturn(post);
        Post postDeleted = this.postService.deletePost(1l);
        Assert.assertNotNull(postDeleted);
    }

    @Test
    public void alterPostTest() {
        PostAlterDTO postAlterDTO = createAlterPostDTO();
        Optional<Post> postFinded = Optional.of(createPost());
        Post postAlter = createPost();

        postAlter.setTitle("Test alter title");
        postAlter.setDescription("Test alter description");
        postAlter.setDateAlter(LocalDateTime.now());

        Mockito.when(this.postRepository.findById(1l)).thenReturn(postFinded);
        Mockito.when(this.postRepository.save(Mockito.any())).thenReturn(postAlter);
        postAlter = this.postService.alterPost(1l, postAlterDTO);
        Assert.assertNotNull(postAlter);
    }

    @Test
    public void findPostTest() {
        Optional<Post> postFind = Optional.of(createPost());
        Mockito.when(this.postRepository.findById(1l)).thenReturn(postFind);
        Post postFinded = this.postService.findPost(1l);
        Assert.assertNotNull(postFinded);
    }

    @Test
    public void listAllPostTest() {
        List<Post> postList = new ArrayList<>();
        postList.add(createPost());
        Mockito.when(this.postRepository.findAll()).thenReturn(postList);
        List<Post> postListed = this.postService.listPost();
        Assert.assertNotNull(postListed);
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
