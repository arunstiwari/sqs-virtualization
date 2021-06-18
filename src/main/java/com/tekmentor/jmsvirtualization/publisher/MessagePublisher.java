package com.tekmentor.jmsvirtualization.publisher;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessagePublisher {
    private static final String TEST_QUEUE = "DemoQueue";

//    @Autowired
    private QueueMessagingTemplate messagingTemplate;

    public void send(final String messagePayload) {
        log.info("Entering method with payload : {}",messagePayload);
        Message<String> msg = MessageBuilder.withPayload(messagePayload)
                .setHeader("sender", "app1")
                .setHeaderIfAbsent("country", "AE")
                .build();
        messagingTemplate.convertAndSend(TEST_QUEUE, msg);
        log.info("Exiting method ");
    }

    public void sendToFifoQueue(final String messagePayload, final String messageGroupID, final String messageDedupID) {
        log.info("Entering method with payload : {}, messageGroupId: {} , messageDedupID : {}",messagePayload, messageGroupID, messageDedupID);
        Message<String> msg = MessageBuilder.withPayload(messagePayload)
                .setHeader("message-group-id", messageGroupID)
                .setHeader("message-deduplication-id", messageDedupID)
                .build();
        messagingTemplate.convertAndSend(TEST_QUEUE, msg);
        log.info("message sent");
    }
}