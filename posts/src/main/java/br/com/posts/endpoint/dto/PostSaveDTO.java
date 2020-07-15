package br.com.posts.endpoint.dto;

import br.com.posts.endpoint.entity.File;
import br.com.posts.endpoint.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostSaveDTO {

    private String title;
    private String description;
    private List<PostSaveFileDTO> files;

    public Post parsePostDTO() {
        Post post = new Post();
        post.setTitle(this.title);
        post.setDescription(this.description);

        List<File> files = new ArrayList<>();

        Optional<List<PostSaveFileDTO>> optionalList =  Optional.ofNullable(this.files);

        if(optionalList.isPresent()) {
            this.files.forEach(image -> {
                File file = new File();
                file.setData(image.getData());
                file.setPosition(image.getPosition());
                file.setPrincipal(image.getPrincipal());
                file.setName(image.getName());
                file.setPost(post);
                files.add(file);
            });
            post.setFiles(files);
        }

        return post;
    }
}
