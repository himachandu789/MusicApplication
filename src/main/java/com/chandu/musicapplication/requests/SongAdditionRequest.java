package com.chandu.musicapplication.requests;

import com.chandu.musicapplication.model.Songs;
import lombok.*;
import com.chandu.musicapplication.model.SongCategory;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SongAdditionRequest {

    private String songTitle;
    private SongCategory songCategory;
    private String file;
    private String songDescription;

    public Songs toSong() {
        return Songs.builder().
                songTitle(this.songTitle).
                songCategory(this.songCategory).
                songDescription(this.songDescription).
                active(true).
                build();
    }
}
