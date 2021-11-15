package by.morunov.websocket.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Alex Morunov
 */
@Builder
public class ChatMessage {

    @Getter
    private MessageType type;

    @Getter
    private String content;

    @Getter
    private String sender;

    @Getter
    private String time;
}
