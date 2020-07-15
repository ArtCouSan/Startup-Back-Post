package br.com.posts.endpoint.dto;

import br.com.posts.endpoint.entity.File;
import br.com.posts.endpoint.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostSaveFileDTO {

    private String name;
    private Boolean principal;
    private Integer position;
    private String data;

}
