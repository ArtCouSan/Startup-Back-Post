package br.com.posts.endpoint.repository;

import br.com.posts.endpoint.entity.Like;
import br.com.posts.endpoint.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    public Optional<Like> findByPostAndAndIdUser(Post post, String id);
    public List<Optional<Like>> findByPost(Post post);

}
