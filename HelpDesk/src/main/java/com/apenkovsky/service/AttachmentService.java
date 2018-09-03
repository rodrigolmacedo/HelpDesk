package com.apenkovsky.service;

import com.apenkovsky.entity.Attachment;
import com.apenkovsky.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttachmentService {

    void save(Attachment attachment, User user);

    List<Attachment> loadAttachmentsByTicketId(Long ticketId);

    Attachment loadAttachmentById(Long id);

    void saveAttachments(MultipartFile[] attachments, Long ticketId, String username);

    void delete(Long attachmentId, String username);

}
