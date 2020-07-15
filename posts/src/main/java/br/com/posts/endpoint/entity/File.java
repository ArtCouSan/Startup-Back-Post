package br.com.posts.endpoint.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TB_FILE")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID")
    private Long id;

    @Column(name = "FILE_NAME")
    private String name;

    @Lob
    @Column(name = "FILE_DATA")
    private String data;

    @Column(name = "FILE_PRINCIPAL")
    private Boolean principal;

    @Column(name = "FILE_POSITION")
    private Integer position;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "POST_ID")
    private Post post;

}
