package com.chandu.musicapplication.requests;

import com.chandu.musicapplication.model.FeedbackRating;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackRequest {

    @NotBlank(message = "Feedback description should not be blank.")
    private String feedbackDescription;

    private FeedbackRating feedbackRating;
}
