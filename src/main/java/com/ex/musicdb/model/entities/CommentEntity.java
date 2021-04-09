package com.ex.musicdb.model.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity{


    private String textContent;
}
