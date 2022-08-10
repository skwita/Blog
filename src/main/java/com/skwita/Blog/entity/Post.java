package com.skwita.Blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Post {
    @Id
    private long id;
    private String title;
    private String text;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
