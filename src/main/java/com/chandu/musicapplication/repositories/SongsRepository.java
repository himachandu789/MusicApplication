package com.chandu.musicapplication.repositories;

import com.chandu.musicapplication.model.Songs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongsRepository extends JpaRepository<Songs, Integer> {

    public Songs findBySongTitle(String title);
}
