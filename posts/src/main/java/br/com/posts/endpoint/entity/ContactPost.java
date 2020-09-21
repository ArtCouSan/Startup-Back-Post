package br.com.posts.endpoint.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TB_CONTACT_POST")
public class ContactPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTACT_POST_ID")
    private Long id;

    @Column(name = "PHONE")
    private String phone;

    @ManyToOne
    @JoinColumn(name="POST_ID")
    private Post post;

}
