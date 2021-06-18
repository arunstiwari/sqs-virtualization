package com.tekmentor.jmsvirtualization.controller;

import com.tekmentor.jmsvirtualization.model.getqueueurl.GetQueueUrlResponse;
import com.tekmentor.jmsvirtualization.model.sendmessagebatch.SendMessageBatchResponse;
import com.tekmentor.jmsvirtualization.provider.IResponseProvider;
import com.tekmentor.jmsvirtualization.service.SQSService;
import com.tekmentor.jmsvirtualization.service.WiremockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController()
@Slf4j
public class SQSHandler {

    @Autowired
    private SQSService sqsService;

    @Autowired
    private WiremockService wiremockService;

    @Autowired
    private IResponseProvider responseProvider;

    @PostMapping(value = "/" ,produces = MediaType.APPLICATION_ATOM_XML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public GetQueueUrlResponse handleDefault(){
        log.info("Entering the default handler");
         return responseProvider.getQueueUrlResponse("DemoQueue");
    }

    @PostMapping( value = "/{accountId}/{queueName}",produces = MediaType.APPLICATION_ATOM_XML_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public SendMessageBatchResponse  addMessage(@PathVariable String accountId, @PathVariable String queueName){
        log.info("Inside addMessage, accountId: {}, queueName: {} ",accountId, queueName);
//        sqsService.addMessage();
        return responseProvider.getSendMessageBatchResponse();
    }

}
