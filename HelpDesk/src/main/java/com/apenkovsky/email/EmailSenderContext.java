package com.apenkovsky.email;

import com.apenkovsky.email.senders.EmailSender;
import com.apenkovsky.entity.User;
import com.apenkovsky.enums.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
@PropertySource("classpath:mail/emailtemplates.properties")
public class EmailSenderContext {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private Environment environment;

    @PostConstruct
    private void init() {
        subjects = new HashMap() {{
            put(EmailTemplate.TICKET_WAS_DONE.name(), "Ticket was done");
            put(EmailTemplate.TICKET_WAS_DECLINED.name(), "Ticket was declined");
            put(EmailTemplate.TICKET_WAS_CANCELLED_BY_MANAGER.name(), "Ticket was cancelled");
            put(EmailTemplate.TICKET_WAS_CANCELLED_BY_ENGINEER.name(), "Ticket was cancelled");
            put(EmailTemplate.TICKET_WAS_APPROVED.name(), "Ticket was approved");
            put(EmailTemplate.NEW_TICKET_FOR_APPROVAL.name(), "New ticket for approval");
            put(EmailTemplate.FEEDBACK_WAS_PROVIDED.name(), "Feedback was provided");
        }};
    }

    private Map<String, String> subjects;

    public EmailSenderContext() {
    }

    public void sendEmail(EmailTemplate template, List<User> recipients, Long ticketId) {
        if (recipients != null && ticketId != 0) {
            emailSender.send(Locale.ENGLISH, recipients, ticketId, environment.getProperty(template.name()),
                    subjects.get(template.name()));
        }
    }
}
