package com.chandu.musicapplication.controllers;

import com.chandu.musicapplication.model.Feedback;
import com.chandu.musicapplication.requests.FeedbackRequest;
import com.chandu.musicapplication.requests.SongAdditionRequest;
import com.chandu.musicapplication.response.GenericMessages;
import com.chandu.musicapplication.response.GenericResponse;
import com.chandu.musicapplication.response.SongAdditionResponse;
import com.chandu.musicapplication.services.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/songs/")
public class SongsController {
    @Autowired
    private SongsService songsService;

    @PostMapping("/addition/")
    public GenericResponse<Object> songAddition(@Valid @RequestBody SongAdditionRequest songAdditionRequest){
    SongAdditionResponse songAdditionResponse = songsService.songAddition(songAdditionRequest);
        if(songAdditionResponse.getMessage().toString().equalsIgnoreCase(GenericMessages.SONGADDITIONSUCCESS.getMessage())) {
            return GenericResponse.builder().
                    code(HttpStatus.OK.value()).
                    message(GenericMessages.SONGADDITIONSUCCESS.getMessage()).
                    statusCode(0).
                    data(songAdditionResponse).build();
        }

        else if (songAdditionResponse.getMessage().equalsIgnoreCase(GenericMessages.ENTRYALREADYPRESENT.getMessage())) {
            return GenericResponse.builder().
                    code(HttpStatus.OK.value()).
                    message(GenericMessages.ENTRYALREADYPRESENT.getMessage()).
                    statusCode(1).
                    data(songAdditionResponse).build();

        }
        else if (songAdditionResponse.getMessage().equalsIgnoreCase(GenericMessages.USERNOTPRESENTTOCREATESONG.getMessage())) {
            return GenericResponse.builder().
                    code(HttpStatus.OK.value()).
                    message(GenericMessages.USERNOTPRESENTTOCREATESONG.getMessage()).
                    statusCode(1).
                    data(songAdditionResponse).build();
        }
        return GenericResponse.builder().
                code(HttpStatus.INTERNAL_SERVER_ERROR.value()).
                message(GenericMessages.SONGADDITIONFAILURE.getMessage()).
                statusCode(1).
                data(null).
                build();
    }

    @PostMapping("/{songId}/feedback")
    public GenericResponse<Object> addFeedbackForSong(@PathVariable Integer songId,
                                                      @Valid @RequestBody FeedbackRequest feedbackRequest) {
        // Here, you can implement the logic to add feedback for the song with the given songId
        // The feedbackRequest object will contain the feedback details provided in the JSON request

        // Sample implementation (not the complete logic):
        Feedback feedback = feedbackRequest.toFeedback();
        // Save the feedback in the database and associate it with the song using songId

        // Return a response indicating success or failure
        return GenericResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Feedback added successfully.")
                .statusCode(0)
                .data(null)
                .build();
    }
}
