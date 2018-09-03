package com.apenkovsky.email.senders;

import com.apenkovsky.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@PropertySource("classpath:mail/emailconfig.properties")
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine htmlTemplateEngine;

    @Autowired
    private Environment environment;

    @Autowired
    private Logger logger;

    public void send(Locale locale, List<User> recipients, Long ticketId, String template, String subject) {
        try {
            final Context context = new Context(locale);
            context.setVariable("ticketId", ticketId);

            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setSubject(subject);
            helper.setFrom(environment.getProperty("sender"));
            ExecutorService emailExecutorService = Executors.newSingleThreadExecutor();
            emailExecutorService.execute(() -> {
                try {
                    for (User recipient : recipients) {
                        context.setVariable("recipient", recipient);
                        helper.setTo(recipient.getEmail());
                        final String htmlContext = this.htmlTemplateEngine.process(template, context);
                        helper.setText(htmlContext, true);
                        this.mailSender.send(mimeMessage);
                    }
                } catch (MessagingException e) {
                    logger.error(e.getMessage());
                }
            });
            emailExecutorService.shutdown();
        } catch (MessagingException e) {
            logger.error(e.getMessage());
        }
    }

}
