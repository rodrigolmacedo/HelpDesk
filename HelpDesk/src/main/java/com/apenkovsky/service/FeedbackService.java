package com.apenkovsky.service;

import com.apenkovsky.entity.Feedback;

public interface FeedbackService {

    Feedback getFeedback(Long ticketId);

    void leaveFeedback(Long ticketId,Feedback feedback,String username);
}
