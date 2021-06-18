package com.tekmentor.jmsvirtualization.provider;

import com.tekmentor.jmsvirtualization.model.getqueueurl.GetQueueUrlResponse;
import com.tekmentor.jmsvirtualization.model.sendmessagebatch.SendMessageBatchResponse;

public interface IResponseProvider {
    GetQueueUrlResponse getQueueUrlResponse(String queueName);

    SendMessageBatchResponse getSendMessageBatchResponse();
}
