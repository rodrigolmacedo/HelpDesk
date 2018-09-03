package com.apenkovsky.converters;

import com.apenkovsky.converters.TicketConverter;
import com.apenkovsky.dto.TicketDto;
import com.apenkovsky.entity.Category;
import com.apenkovsky.entity.Ticket;
import com.apenkovsky.entity.User;
import com.apenkovsky.enums.State;
import com.apenkovsky.enums.Urgency;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class TicketConverterTest {

    private User user;

    private  Category category;

    private Ticket ticket;

    private TicketDto ticketDto;

    String json;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private TicketConverter ticketConverter;

    @Before
    public void init() throws IOException {
        MockitoAnnotations.initMocks(this);
        user = new User();
        category = new Category();
        ticket = new Ticket();
        ticket.setId(1L);
        ticket.setName("testTicket");
        ticket.setState(State.DONE);
        ticket.setOwner(user);
        ticket.setDescription("someDescription");
        ticket.setAssignee(user);
        ticket.setCategory(category);
        ticket.setUrgency(Urgency.LOW);
        ticket.setApprover(user);
        ticketDto = new TicketDto();
        ticketDto.setId(1L);
        ticketDto.setName("testTicket");
        ticketDto.setState(State.DONE);
        ticketDto.setOwner(user);
        ticketDto.setDescription("someDescription");
        ticketDto.setAssignee(user);
        ticketDto.setCategory(category);
        ticketDto.setUrgency(Urgency.LOW);
        ticketDto.setApprover(user);
        ticketDto.setComment("someComment");
        json = "{\"name\":\"testTicket\",\"state\":\"DONE\",\"category\":{\"id\":\"1\"},\"urgency\":\"LOW\",\"comment\":\"someComment\",\"description\":\"someDescription\",\"desiredResolutionDate\":1536008400000}";

        Mockito.when(objectMapper.readValue(anyString(),any(Class.class))).thenReturn(ticketDto);

    }

    @Test
    public void testConvertToDtoTicketIsNull() {
        Optional<TicketDto> ticketDto = ticketConverter.convertToDto(null);
        assertFalse(ticketDto.isPresent());
    }

    @Test
    public  void testConvertToDtoTicket(){
        TicketDto dto = ticketConverter.convertToDto(ticket).get();

        assertEquals(ticket.getId(),dto.getId());
        assertEquals(ticket.getName(),dto.getName());
        assertEquals(ticket.getState(),dto.getState());
        assertEquals(ticket.getOwner(),dto.getOwner());
        assertEquals(ticket.getDescription(),dto.getDescription());
        assertEquals(ticket.getAssignee(),dto.getAssignee());
        assertEquals(ticket.getCategory(),dto.getCategory());
        assertEquals(ticket.getUrgency(),dto.getUrgency());
        assertEquals(ticket.getApprover(),dto.getApprover());

    }

    @Test
    public  void testConvertToEntity(){

        Ticket result = ticketConverter.convertToEntity(ticketDto).get();

        assertEquals(ticketDto.getId(),result.getId());
        assertEquals(ticketDto.getName(),result.getName());
        assertEquals(ticketDto.getState(),result.getState());
        assertEquals(ticketDto.getOwner(),result.getOwner());
        assertEquals(ticketDto.getDescription(),result.getDescription());
        assertEquals(ticketDto.getAssignee(),result.getAssignee());
        assertEquals(ticketDto.getCategory(),result.getCategory());
        assertEquals(ticketDto.getUrgency(),result.getUrgency());
        assertEquals(ticketDto.getApprover(),result.getApprover());
    }

    @Test
    public  void testConvertFromJson(){

        TicketDto dto = ticketConverter.convertFromJson(json).get();


        assertEquals("testTicket",dto.getName());
        assertEquals("someComment",dto.getComment());
        assertEquals(State.DONE,dto.getState());
        assertEquals(Urgency.LOW,dto.getUrgency());
        assertEquals("someDescription", dto.getDescription());

    }
}
