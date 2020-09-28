package br.com.posts.endpoint.service.impl;

import br.com.posts.endpoint.dto.PostAlterDTO;
import br.com.posts.endpoint.dto.PostSaveDTO;
import br.com.posts.endpoint.entity.Post;
import br.com.posts.endpoint.enums.PostStatusEnum;
import br.com.posts.endpoint.repository.PostRepository;
import br.com.posts.endpoint.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post savePost(PostSaveDTO postSaveDTO) {
        Post post = postSaveDTO.parsePostDTO();
        post.setDateInsert(this.dateNow());
        post.setStatus(PostStatusEnum.ACTIVE);
        post = postRepository.save(post);
        return post;
    }

    @Override
    public Post alterPost(Long id, PostAlterDTO postAlterDTO) {
        Post post = postExist(id);
        Post postAlter = postAlterDTO.parsePostDTO();
        post.setTitle(postAlter.getTitle() != null ? postAlter.getTitle() : post.getTitle());
        post.setDescription(postAlter.getDescription() != null ? postAlter.getDescription() : post.getDescription());
        post.setDateAlter(this.dateNow());
        post.setHelpBudget(postAlter.getHelpBudget());
        post.setHelpWork(postAlter.getHelpWork());
        post.setResponsible(postAlter.getResponsible() != null ? postAlter.getResponsible() : post.getResponsible());
        post.setCep(postAlter.getCep() != null ? postAlter.getCep() : post.getCep());
        post.setAddressComplement(postAlter.getAddressComplement() != null ? postAlter.getAddressComplement() : post.getAddressComplement());
        post.setCellphone(postAlter.getCellphone() != null ? postAlter.getCellphone() : post.getCellphone());

        return postRepository.save(post);
    }

    @Override
    public Post deletePost(Long id) {
        Post post = postExist(id);
        post.setDateDelete(this.dateNow());
        post.setStatus(PostStatusEnum.DISABLE);
        return postRepository.save(post);
    }

    @Override
    public Post findPost(Long id) {
        Post post = postExist(id);
        return post;
    }

    @Override
    public List<Post> listPost() {
        return postRepository.findAll();
    }

    private LocalDateTime dateNow(){
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        return zonedDateTime.toLocalDateTime();
    }

    private Post postExist(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            return optionalPost.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post by id: ".concat(id.toString()).concat(" not found."));
        }
    }
}
