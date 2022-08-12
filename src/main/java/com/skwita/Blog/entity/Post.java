package com.skwita.Blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @NotEmpty(message = "Title must not be empty")
    @Size(max=30, message = "Title must must not be more than 30 characters long")
    private String title;
    @NotNull
    @NotEmpty(message = "Text must not be empty")
    @Size(max=300, message="Text must not be more than 300 characters long")
    private String text;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Post(String title, String text, User user) {
        this.title = title;
        this.text = text;
        this.user = user;
    }
}
