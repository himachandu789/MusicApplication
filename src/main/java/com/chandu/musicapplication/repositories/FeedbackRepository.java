package com.chandu.musicapplication.repositories;

import com.chandu.musicapplication.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository <Feedback, Integer> {
}
