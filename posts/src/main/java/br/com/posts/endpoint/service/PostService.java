package br.com.posts.endpoint.service;

import br.com.posts.endpoint.dto.PostAlterDTO;
import br.com.posts.endpoint.dto.PostListDTO;
import br.com.posts.endpoint.dto.PostSaveDTO;
import br.com.posts.endpoint.entity.Post;

import java.util.List;

public interface PostService {

    public Post savePost(PostSaveDTO postSaveDTO);
    public Post alterPost(Long id, PostAlterDTO postAlterDTO);
    public Post deletePost(Long id);
    public Post findPost(Long id);
    public List<Post> listPost();

}
