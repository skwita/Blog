package com.skwita.Blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Size(max=30, message = "Title must not be more than 30 characters long")
    private String title;
    @NotNull
    @NotEmpty(message = "Text must not be empty")
    @Size(max=300, message="Text must not be more than 300 characters long")
    private String text;
    //@NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> userLikes;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments;

    public Post(String title, String text, User user) {
        this.title = title;
        this.text = text;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public Set<Long> getUserLikesIds() {
        return this.userLikes.stream().map(User::getId).collect(Collectors.toSet());
    }
}
