package com.tekmentor.jmsvirtualization.model.getqueueurl;

import com.amazonaws.ResponseMetadata;

public final class GetQueueUrlResponseBuilder {
    private GetQueueUrlResult GetQueueUrlResult;
    private ResponseMetadata responseMetadata;

    private GetQueueUrlResponseBuilder() {
    }

    public static GetQueueUrlResponseBuilder aGetQueueUrlResponse() {
        return new GetQueueUrlResponseBuilder();
    }

    public GetQueueUrlResponseBuilder withGetQueueUrlResult(GetQueueUrlResult GetQueueUrlResult) {
        this.GetQueueUrlResult = GetQueueUrlResult;
        return this;
    }

    public GetQueueUrlResponseBuilder withResponseMetadata(ResponseMetadata responseMetadata) {
        this.responseMetadata = responseMetadata;
        return this;
    }

    public GetQueueUrlResponse build() {
        GetQueueUrlResponse getQueueUrlResponse = new GetQueueUrlResponse();
        getQueueUrlResponse.setGetQueueUrlResult(GetQueueUrlResult);
        getQueueUrlResponse.setResponseMetadata(responseMetadata);
        return getQueueUrlResponse;
    }
}
