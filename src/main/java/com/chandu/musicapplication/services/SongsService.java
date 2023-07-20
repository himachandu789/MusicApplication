package com.chandu.musicapplication.services;

import com.chandu.musicapplication.model.Songs;
import com.chandu.musicapplication.model.User;
import com.chandu.musicapplication.repositories.SongCacheRepository;
import com.chandu.musicapplication.repositories.SongsRepository;
import com.chandu.musicapplication.repositories.UserCacheRepository;
import com.chandu.musicapplication.repositories.UserRepository;
import com.chandu.musicapplication.requests.SongAdditionRequest;
import com.chandu.musicapplication.response.GenericMessages;
import com.chandu.musicapplication.response.SongAdditionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SongsService {

    @Autowired
    private SongsRepository songsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongCacheRepository songCacheRepository;

    @Autowired
    private UserCacheRepository userCacheRepository;
    public SongAdditionResponse songAddition(SongAdditionRequest songAdditionRequest) {
        if(songsRepository.findBySongTitle(songAdditionRequest.getSongTitle()) != null){
            return SongAdditionResponse.builder().message(GenericMessages.ENTRYALREADYPRESENT.getMessage()).build();
        }
        Songs songInDb = songAdditionRequest.toSong();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user =  (User) authentication.getPrincipal();
        User userFromDb = null;
        if(userCacheRepository.get(user.getMail()) != null){
            userFromDb = userCacheRepository.get(user.getMail());
        }else {
            userFromDb = userRepository.findByMail(user.getMail());
        }

        if(userFromDb != null){
            songInDb.setCreatedBy(userFromDb);
        }else{
            return SongAdditionResponse.builder().message(GenericMessages.USERNOTPRESENTTOCREATESONG.getMessage()).build();
        }
        songCacheRepository.set(songInDb);
        try{
            songInDb = songsRepository.save(songInDb);
        }catch (Exception e){
            e.printStackTrace();
            return SongAdditionResponse.builder().message(GenericMessages.SONGADDITIONFAILURE.getMessage()).build();
        }
        return SongAdditionResponse.builder().
                message(GenericMessages.SONGADDITIONSUCCESS.getMessage()).
                songId(songInDb.getPk()).
                songPath("").
                build();
    }
}
