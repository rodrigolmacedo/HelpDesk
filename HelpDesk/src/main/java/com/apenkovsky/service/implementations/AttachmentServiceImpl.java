package com.apenkovsky.service.implementations;

import com.apenkovsky.entity.Attachment;
import com.apenkovsky.entity.Ticket;
import com.apenkovsky.entity.User;
import com.apenkovsky.repository.AttachmentRepository;
import com.apenkovsky.service.AttachmentService;
import com.apenkovsky.service.HistoryService;
import com.apenkovsky.service.TicketService;
import com.apenkovsky.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    public void save(Attachment attachment, User user) {
        attachmentRepository.save(attachment);
    }

    public List<Attachment> loadAttachmentsByTicketId(Long ticketId) {
        return attachmentRepository.loadAttachmetsByTicketId(ticketId);
    }

    public Attachment loadAttachmentById(Long id) {
        return attachmentRepository.loadAttachmentById(id);
    }

    public void delete(Long attachmentId, String username) {
        Attachment attachment = loadAttachmentById(attachmentId);
        User currentUser = userService.loadUserByEmail(username);
        if (attachment == null || currentUser == null) {
            throw new IllegalArgumentException();
        }
        attachmentRepository.delete(attachmentId);
    }

    public void saveAttachments(MultipartFile[] attachments, Long ticketId, String username) {
        User user = userService.loadUserByEmail(username);
        Ticket ticket = ticketService.getTicketById(ticketId);
        if (ticket == null || user == null) {
            throw new IllegalArgumentException();
        }
        for (MultipartFile file : attachments) {
            try {
                if (file.getBytes().length > 0) {
                    Attachment attachment = new Attachment();
                    attachment.setBlob(file.getBytes());
                    attachment.setTicket(ticket);
                    attachment.setContentType(file.getContentType());
                    attachment.setName(file.getOriginalFilename());
                    save(attachment, user);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
