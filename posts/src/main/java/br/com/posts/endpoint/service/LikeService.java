package br.com.posts.endpoint.service;

import br.com.posts.endpoint.dto.LikeDTO;
import br.com.posts.endpoint.entity.Like;
import br.com.posts.endpoint.entity.Post;

public interface LikeService {

    public Post likePost(Long idPost, LikeDTO likeDTO);

}
