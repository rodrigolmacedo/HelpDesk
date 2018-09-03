package com.apenkovsky.repository;

import com.apenkovsky.entity.Attachment;


import java.util.List;

public interface AttachmentRepository {

    List<Attachment> loadAttachmetsByTicketId(Long ticketId);

    Attachment loadAttachmentById(Long id);

    void save(Attachment attachment);

    void delete(Long attachmentId);

}
