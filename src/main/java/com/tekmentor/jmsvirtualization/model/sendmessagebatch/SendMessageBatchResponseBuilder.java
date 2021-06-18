package com.tekmentor.jmsvirtualization.model.sendmessagebatch;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sqs.model.SendMessageBatchResult;

public final class SendMessageBatchResponseBuilder {
    private SendMessageBatchResult sendMessageBatchResult;
    private ResponseMetadata responseMetadata;

    private SendMessageBatchResponseBuilder() {
    }

    public static SendMessageBatchResponseBuilder aSendMessageBatchResponse() {
        return new SendMessageBatchResponseBuilder();
    }

    public SendMessageBatchResponseBuilder withSendMessageBatchResult(SendMessageBatchResult sendMessageBatchResult) {
        this.sendMessageBatchResult = sendMessageBatchResult;
        return this;
    }

    public SendMessageBatchResponseBuilder withResponseMetadata(ResponseMetadata responseMetadata) {
        this.responseMetadata = responseMetadata;
        return this;
    }

    public SendMessageBatchResponse build() {
        SendMessageBatchResponse sendMessageBatchResponse = new SendMessageBatchResponse();
        sendMessageBatchResponse.setSendMessageBatchResult(sendMessageBatchResult);
        sendMessageBatchResponse.setResponseMetadata(responseMetadata);
        return sendMessageBatchResponse;
    }
}
