package com.ex.musicdb.model.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
public class LikeEntity extends BaseEntity {


    private Integer likes;
    @ManyToOne
    private UserEntity user;
    @ManyToOne
    private ArticleEntity articleEntity;


    public LikeEntity() {
    }

    public Integer getLikes() {
        return likes;
    }

    public LikeEntity setLikes(Integer likes) {
        this.likes = likes;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public LikeEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public ArticleEntity getArticleEntity() {
        return articleEntity;
    }

    public LikeEntity setArticleEntity(ArticleEntity articleEntity) {
        this.articleEntity = articleEntity;
        return this;
    }
}
