package com.tekmentor.jmsvirtualization.util;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;
import com.tekmentor.jmsvirtualization.config.SQSConfiguration;
import com.tekmentor.jmsvirtualization.extensions.SQSResponseDefinitionTransformer;
import lombok.extern.slf4j.Slf4j;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@Slf4j
public class SQSWireMock {
    private WireMockServer wiremockServer;
    private SQSConfiguration configuration;
    private SQSResponseDefinitionTransformer sqsResponseDefinitionTransformer;

    public SQSWireMock(SQSConfiguration configuration, SQSResponseDefinitionTransformer sqsResponseDefinitionTransformer) {
        this.configuration = configuration;
        this.sqsResponseDefinitionTransformer = sqsResponseDefinitionTransformer;
       init();
    }

    private void init() {
        WireMockConfiguration wireMockConfiguration = wireMockConfig();
        wireMockConfiguration.containerThreads(25)
                .jettyAcceptors(10)
                .asynchronousResponseEnabled(true)
                .asynchronousResponseThreads(20)
                .networkTrafficListener();
        this.wiremockServer = new WireMockServer(wireMockConfiguration);
        this.wiremockServer.addMockServiceRequestListener(
                SQSWireMock::requestReceived
        );
        WireMock.configureFor("localhost",configuration.getWiremockPort());

    }

    public void startWireMockServer(){
        log.info("Going to start WireMockServer");
        if (this.wiremockServer == null){
            init();
            this.wiremockServer.start();
            log.info("WiremockServer started");
        }else if (this.wiremockServer != null && this.wiremockServer.isRunning()){
            log.info("Wiremock Server is already running");
        }else{
            this.wiremockServer.start();
            log.info("WiremockServer started");
        }
    }

    public void stopWireMockServer(){
        log.info("Going to stop the WireMockServer");
        if (wiremockServer != null && this.wiremockServer.isRunning()) {
            this.wiremockServer.stop();
            log.info("WiremockServer is stopped");
        }else{
            log.info("WiremockServer is already stopped");
        }
    }


    private WireMockConfiguration getWireMockConfiguration(){
        return wireMockConfig()
                .port(configuration.getWiremockPort())
                .withRootDirectory(configuration.getRootDir())
                .extensions(sqsResponseDefinitionTransformer);
    }

    public void setSqsResponseDefinitionTransformer(SQSResponseDefinitionTransformer sqsResponseDefinitionTransformer) {
        this.sqsResponseDefinitionTransformer = sqsResponseDefinitionTransformer;
    }

    protected static void requestReceived(Request request, Response response){
        log.info("Wiremock request at URL: {}",request.getAbsoluteUrl());
        log.info("Wiremock request headers: {}",request.getHeaders());
        log.info("Wiremock response body: {}",response.getBodyAsString());
        log.info("Wiremock response headers: {}",response.getHeaders());
    }
}
