package com.tekmentor.jmsvirtualization.model.sendmessagebatch;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.services.sqs.model.SendMessageBatchResult;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SendMessageBatchResponse {
    @JacksonXmlProperty(localName = "SendMessageBatchResult")
    private SendMessageBatchResult sendMessageBatchResult;
    @JacksonXmlProperty(localName = "ResponseMetadata")
    private ResponseMetadata responseMetadata;
}
