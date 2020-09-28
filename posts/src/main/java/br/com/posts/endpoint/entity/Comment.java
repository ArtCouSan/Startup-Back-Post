package br.com.posts.endpoint.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TB_COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(name = "DT_CREATED", nullable = false)
    private LocalDateTime dtCreated;

    @Column(name = "DT_UPDATE", nullable = false)
    private LocalDateTime dtUpdate;

    @Column(name = "TEXT", nullable = false, length = 255)
    private String text;

    @ManyToOne
    @JoinColumn(name="POST_ID")
    private Post post;

    @Column(name = "ID_USER", nullable = false)
    private String usuario;

}
