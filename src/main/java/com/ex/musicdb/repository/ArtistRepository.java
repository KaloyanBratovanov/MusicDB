package com.ex.musicdb.repository;

import com.ex.musicdb.model.entities.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity,Long> {


    @Query("SELECT a.name FROM ArtistEntity as a")
    List<String> findAllArtistNames();
}
