package br.com.posts.endpoint.dto;

import br.com.posts.endpoint.entity.File;
import br.com.posts.endpoint.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostAlterDTO {

    private String title;
    private String description;
    private List<PostAlterFileDTO> files;
    private String addressComplement;
    private String cep;
    private String cellphone;
    private String howHelp;
    private String responsible;
    private Boolean helpWork;
    private Boolean helpBudget;
    private String category;
    private String email;
    private BigDecimal helpBudgetAmount;
    private String helpWorkCategory;

    public Post parsePostDTO() {
        Post post = new Post();
        post.setTitle(this.title);
        post.setDescription(this.description);
        post.setAddressComplement(this.addressComplement);
        post.setCellphone(cellphone);
        post.setCep(this.cep);
        post.setResponsible(this.responsible);
        post.setHelpWork(this.helpWork);
        post.setHelpBudget(this.helpBudget);
        post.setHowHelp(this.howHelp);
        post.setCategory(this.category);
        post.setEmail(this.email);
        post.setHelpBudgetAmount(this.helpBudgetAmount);
        post.setHelpWorkCategory(this.helpWorkCategory);

        List<File> files = new ArrayList<>();

        Optional<List<PostAlterFileDTO>> optionalList = Optional.ofNullable(this.files);

        if (optionalList.isPresent()) {

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
