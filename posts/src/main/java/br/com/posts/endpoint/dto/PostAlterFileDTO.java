package br.com.posts.endpoint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostAlterFileDTO {

    private String name;
    private Boolean principal;
    private Integer position;
    private String data;

}
