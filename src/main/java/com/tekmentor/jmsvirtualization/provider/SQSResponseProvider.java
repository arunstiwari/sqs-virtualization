package com.tekmentor.jmsvirtualization.provider;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sqs.model.SendMessageBatchResult;
import com.amazonaws.services.sqs.model.SendMessageBatchResultEntry;
import com.tekmentor.jmsvirtualization.config.SQSConfiguration;
import com.tekmentor.jmsvirtualization.model.getqueueurl.GetQueueUrlResponse;
import com.tekmentor.jmsvirtualization.model.getqueueurl.GetQueueUrlResponseBuilder;
import com.tekmentor.jmsvirtualization.model.getqueueurl.GetQueueUrlResult;
import com.tekmentor.jmsvirtualization.model.sendmessagebatch.SendMessageBatchResponse;
import com.tekmentor.jmsvirtualization.model.sendmessagebatch.SendMessageBatchResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.amazonaws.ResponseMetadata.AWS_REQUEST_ID;
import static com.tekmentor.jmsvirtualization.util.URLConstructor.constructUrl;

@Service
@Slf4j
public class SQSResponseProvider implements IResponseProvider {

    @Autowired
    private SQSConfiguration configuration;

    @Override
    public GetQueueUrlResponse getQueueUrlResponse(String queueName) {
        GetQueueUrlResult result = new GetQueueUrlResult().withQueueUrl(constructUrl(configuration.getServerPort(),queueName));

        return GetQueueUrlResponseBuilder
                    .aGetQueueUrlResponse()
                    .withResponseMetadata(getResponseMetadata())
                    .withGetQueueUrlResult(result)
                    .build();
    }

    @Override
    public SendMessageBatchResponse getSendMessageBatchResponse() {
        SendMessageBatchResult batchResult =  new SendMessageBatchResult();

        Collection<SendMessageBatchResultEntry> successful = getSendMessageBatchResultEntries();
        batchResult.setSuccessful(successful);

        SendMessageBatchResponse response = SendMessageBatchResponseBuilder
                .aSendMessageBatchResponse()
                .withResponseMetadata(getResponseMetadata())
                .withSendMessageBatchResult(batchResult)
                .build();
        return response;
    }

    private Collection<SendMessageBatchResultEntry> getSendMessageBatchResultEntries() {
        Collection<SendMessageBatchResultEntry> successful = new ArrayList<>();
        SendMessageBatchResultEntry entry = new SendMessageBatchResultEntry();
        entry.setId("test_msg_001");
        entry.setMD5OfMessageBody("0e024d309850c78cba5eabbeff7cae71");
        entry.setMessageId("0a5231c7-8bff-4955-be2e-8dc7c50a25fa");
        successful.add(entry);
        return successful;
    }

    private ResponseMetadata getResponseMetadata() {
        Map<String, String> metadata = new HashMap<>();
        metadata.put(AWS_REQUEST_ID, "470a6f13-2ed9-4181-ad8a-2fdea142988e");
        return new ResponseMetadata(metadata);
    }
}
