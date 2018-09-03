package com.apenkovsky.controller.rest;

import com.apenkovsky.entity.Attachment;
import com.apenkovsky.service.AttachmentService;
import com.apenkovsky.validators.TicketValidator;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class AttachmentRestController {

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private TicketValidator ticketValidator;

    @GetMapping(value = "/ticket/{ticketId}/attachments/{attachmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> getAttachment(@PathVariable Long ticketId,
                                                @PathVariable Long attachmentId, HttpServletResponse response) {
        Attachment ticketAttachment = attachmentService.loadAttachmentById(attachmentId);
        response.setContentType(ticketAttachment.getContentType());
        String filename = ticketAttachment.getName();
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-desposition", "inline;filename=" + filename);
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(ticketAttachment.getBlob(), headers, HttpStatus.OK);
    }

    @DeleteMapping(value = "/ticket/{ticketId}/attachments/{attachmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAttachment(@PathVariable Long ticketId,
                                 @PathVariable Long attachmentId,
                                 HttpServletResponse response,
                                 Authentication authentication) throws InvalidDataException {
        if (ticketValidator.isValidForDeletingAttachment(ticketId, authentication.getName())) {
            attachmentService.delete(attachmentId, authentication.getName());
        } else {
            throw new InvalidDataException("Invalid data!");
        }
    }

    @PostMapping(value = "/ticket/{ticketId}/attachments")
    public void addAttachments(@RequestParam(required = false) MultipartFile[] files,
                               @PathVariable Long ticketId,
                               HttpServletResponse response,
                               Authentication authentication) throws InvalidDataException {
        if (ticketValidator.isValidForAddingAttachment(ticketId, authentication.getName())) {
            attachmentService.saveAttachments(files, ticketId, authentication.getName());
        } else {
            throw new InvalidDataException("Invalid data!");
        }
    }

    @GetMapping(value = "/ticket/{ticketId}/attachments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Attachment> getTicketAttachments(@PathVariable Long ticketId) {
        return attachmentService.loadAttachmentsByTicketId(ticketId);
    }
}
