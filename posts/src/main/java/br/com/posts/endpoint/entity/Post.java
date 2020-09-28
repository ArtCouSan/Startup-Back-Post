package br.com.posts.endpoint.entity;

import br.com.posts.endpoint.enums.PostStatusEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_POST")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(name = "POST_TITLE")
    private String title;

    @Column(name = "POST_DESCRIPTION")
    private String description;

    @Column(name = "POST_ID_USER")
    private String idUser;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", fetch = FetchType.EAGER)
    private List<File> files;

    @Column(name = "POST_DATE_INSERT")
    private LocalDateTime dateInsert;

    @Column(name = "POST_DATE_ALTER")
    private LocalDateTime dateAlter;

    @Column(name = "POST_DATE_DELETE")
    private LocalDateTime dateDelete;

    @Enumerated(EnumType.STRING)
    @Column(name = "POST_STATUS")
    private PostStatusEnum status;

    @Column(name = "POST_HELP_WORK")
    private Boolean helpWork;

    @Column(name = "POST_HELP_BUDGET")
    private Boolean helpBudget;

    @Column(name = "POST_CEP_COMPLEMENT")
    private String addressComplement;

    @Column(name = "POST_CEP")
    private String cep;

    @Column(name = "POST_CELLPHONE")
    private String cellphone;

    @Column(name = "POST_EMAIL")
    private String email;

    @Column(name = "POST_HELP_BUDGET_AMOUNT")
    private BigDecimal helpBudgetAmount;

    @Column(name = "POST_HELP_WORK_CATEGORY")
    private String helpWorkCategory;

    @Column(name = "POST_RESPONSIBLE")
    private String responsible;

    @Column(name = "POST_HOW_HELP")
    private String howHelp;

    @Column(name = "POST_CATEGORY")
    private String category;

    @OneToMany(mappedBy = "post")
    private List<Like> likes;

}
