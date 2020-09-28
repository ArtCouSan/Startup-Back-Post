package br.com.posts.endpoint.service;

import br.com.posts.endpoint.dto.CommentDTO;
import br.com.posts.endpoint.entity.Comment;

public interface CommentService {

    public Comment commentSave(Long idPost, CommentDTO commentDTO);

}
