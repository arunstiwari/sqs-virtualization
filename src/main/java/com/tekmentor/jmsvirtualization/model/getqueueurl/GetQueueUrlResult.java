package com.tekmentor.jmsvirtualization.model.getqueueurl;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetQueueUrlResult {

    @JacksonXmlProperty(localName = "QueueUrl")
    private String queueUrl;

    public GetQueueUrlResult withQueueUrl(String constructUrl) {
        this.queueUrl = constructUrl;
        return this;
    }
}
