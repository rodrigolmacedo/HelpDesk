package com.apenkovsky.converters;

import com.apenkovsky.dto.HistoryItemDto;
import com.apenkovsky.entity.HistoryItem;
import com.apenkovsky.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HistoryConverter {

    public Optional<HistoryItemDto> convertToDto(final HistoryItem historyItem, final User user) {
        Optional<HistoryItemDto> maybeResult = Optional.empty();
        if (historyItem != null && user != null) {
            maybeResult = Optional.of(HistoryItemDto.HistoryItemDtoBuilder.aHistoryItemDto()
                    .withId(historyItem.getId())
                    .withDate(historyItem.getDate())
                    .withAction(historyItem.getAction())
                    .withTicket(historyItem.getTicket())
                    .withUserFirstName(user.getFirstName())
                    .withUserLastName(user.getLastName())
                    .withDescription(historyItem.getDescription())
                    .build());
        }
        return maybeResult;
    }
}
