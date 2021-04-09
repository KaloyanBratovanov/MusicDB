package com.ex.musicdb.repository;

import com.ex.musicdb.model.entities.ArticleEntity;
import com.ex.musicdb.model.view.ArticleViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    Optional<ArticleViewModel> findTopByOrderByCreatedOnDesc();
}
