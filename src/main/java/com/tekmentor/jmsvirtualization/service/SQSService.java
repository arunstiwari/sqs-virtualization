package com.tekmentor.jmsvirtualization.service;

import com.tekmentor.jmsvirtualization.publisher.MessagePublisher;
import com.tekmentor.jmsvirtualization.subscriber.MessageSubscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class SQSService {
    AtomicInteger counter = new AtomicInteger(0);
    private Queue<String> messages = new ConcurrentLinkedQueue<>();
    @Autowired
    MessagePublisher publisher;

    @Autowired
    MessageSubscriber subscriber;

    public String fetchMessage(String queueName) {
        log.info("queueName : {}",queueName);
        log.info("Messages size : {} ",messages.size());
        String message = messages.poll();
        counter.decrementAndGet();
        log.info("Message Retrieved : {}",message);
        log.info("Messages queue size : {}",messages.size());
        return message;
    }


    public void addMessage(String message) {
        log.info("New Message : {}",message);
        messages.add(message);
        counter.incrementAndGet();
        log.info("Messages size : {}",messages.size());
//        publisher.send(message);
    }
}
