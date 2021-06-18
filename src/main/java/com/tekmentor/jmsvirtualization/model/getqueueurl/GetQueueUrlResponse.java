package com.tekmentor.jmsvirtualization.model.getqueueurl;

import com.amazonaws.ResponseMetadata;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GetQueueUrlResponse {

    @JacksonXmlProperty(localName = "GetQueueUrlResult")
    private GetQueueUrlResult getQueueUrlResult;

    @JacksonXmlProperty(localName = "ResponseMetadata")
    private ResponseMetadata responseMetadata;
}
