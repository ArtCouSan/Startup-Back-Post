package br.com.posts.endpoint.service.impl;

import br.com.posts.endpoint.dto.LikeDTO;
import br.com.posts.endpoint.entity.Like;
import br.com.posts.endpoint.entity.Post;
import br.com.posts.endpoint.repository.LikeRepository;
import br.com.posts.endpoint.repository.PostRepository;
import br.com.posts.endpoint.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    LikeRepository likeRepository;
    PostRepository postRepository;

    public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Post likePost(Long idPost, LikeDTO likeDTO) {

        Optional<Post> postOptional = postRepository.findById(idPost);

        if(postOptional.isPresent()) {

            Optional<Like> likeOptional = likeRepository.findByPostAndAndIdUser(postOptional.get(), likeDTO.getIdUser());

            if(likeOptional.isPresent()) {
                likeRepository.delete(likeOptional.get());
            }else {
                Like like = new Like();
                like.setDtCriacao(LocalDateTime.now());
                like.setPost(postOptional.get());
                like.setIdUser(likeDTO.getIdUser());
                likeRepository.save(like);
            }

            Optional<Post> postOptionalAfterSaveNewLike = postRepository.findById(idPost);

            return postOptionalAfterSaveNewLike.get();

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post by id: ".concat(idPost.toString()).concat(" not found."));

    }

}
