package com.apenkovsky.repository;

import com.apenkovsky.entity.Feedback;

public interface FeedbackRepository {

    Feedback loadFeedback(Long ticketId);

    void saveFeedback(Feedback feedback);
}
