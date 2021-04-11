package com.ex.musicdb.repository;

import com.ex.musicdb.model.entities.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    long findByArticleEntity_Id(Long id);


}
