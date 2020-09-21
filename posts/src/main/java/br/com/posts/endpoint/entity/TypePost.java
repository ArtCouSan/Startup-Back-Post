package br.com.posts.endpoint.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TB_TYPE_POST")
public class TypePost implements Serializable {

    private static final long serialVersionUID = 7758637812000758045L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TYPE_POST_ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(mappedBy = "types")
    private List<Post> posts;

}
