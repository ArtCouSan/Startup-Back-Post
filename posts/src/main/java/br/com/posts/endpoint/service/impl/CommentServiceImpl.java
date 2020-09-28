package br.com.posts.endpoint.service.impl;

import br.com.posts.endpoint.dto.CommentDTO;
import br.com.posts.endpoint.entity.Comment;
import br.com.posts.endpoint.entity.Like;
import br.com.posts.endpoint.entity.Post;
import br.com.posts.endpoint.repository.CommentRepository;
import br.com.posts.endpoint.repository.PostRepository;
import br.com.posts.endpoint.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;
    PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Comment commentSave(Long idPost, CommentDTO commentDTO) {

        Optional<Post> postOptional = postRepository.findById(idPost);

        if (postOptional.isPresent()) {

            Comment comment = new Comment();
            comment.setText(commentDTO.getMessage());
            comment.setDtCreated(LocalDateTime.now());
            comment.setPost(postOptional.get());
            comment.setUsuario(commentDTO.getIdUser());
            commentRepository.save(comment);

        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post by id: ".concat(idPost.toString()).concat(" not found."));

    }

}
