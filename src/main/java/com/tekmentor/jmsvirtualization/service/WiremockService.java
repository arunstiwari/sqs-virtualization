package com.tekmentor.jmsvirtualization.service;

import com.tekmentor.jmsvirtualization.config.SQSConfiguration;
import com.tekmentor.jmsvirtualization.util.SQSWireMock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WiremockService {

    @Autowired
    private SQSConfiguration sqsConfiguration;

    @Autowired
    private SQSWireMock sqsWireMock;


    public void start(){
        this.sqsWireMock.startWireMockServer();
    }

    public void stop() {
        this.sqsWireMock.stopWireMockServer();
    }

}
