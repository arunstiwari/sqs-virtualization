package com.tekmentor.jmsvirtualization;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekmentor.jmsvirtualization.config.SQSConfiguration;
import com.tekmentor.jmsvirtualization.config.SQSConfigurationBuilder;
import com.tekmentor.jmsvirtualization.extensions.SQSResponseDefinitionTransformer;
import com.tekmentor.jmsvirtualization.util.SQSWireMock;
import io.awspring.cloud.messaging.config.QueueMessageHandlerFactory;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver;

import java.util.Collections;


@Slf4j
@SpringBootApplication
public class JmsVirtualizationApplication {

    @Value("${server.port}")
    private int port;

    @Bean
    public SQSConfiguration sqsConfiguration(){
        SQSConfiguration sqsConfiguration = SQSConfigurationBuilder.aSQSConfiguration()
                .withWiremockPort(Integer.valueOf(System.getProperty("wiremock.server.port")))
                .withRootDir(System.getProperty("wiremock.root.dir", "src/main/resources"))
                .withServerPort(port)
                .build();

        return sqsConfiguration;
    }

    @Bean
    public SQSResponseDefinitionTransformer sqsResponseDefinitionTransformer(){
        return new SQSResponseDefinitionTransformer();
    }

    @Bean
    @DependsOn("sqsResponseDefinitionTransformer")
    public SQSWireMock sqsWireMock(SQSConfiguration sqsConfiguration, SQSResponseDefinitionTransformer sqsResponseDefinitionTransformer){
        return new SQSWireMock(sqsConfiguration, sqsResponseDefinitionTransformer);
    }

//    @Bean
//    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync){
//        return new QueueMessagingTemplate(amazonSQSAsync);
//    }
//
//    @Bean
//    public QueueMessageHandlerFactory queueMessageHandlerFactory(final ObjectMapper mapper,
//                                                                 final AmazonSQSAsync amazonSQSAsync) {
//        final QueueMessageHandlerFactory queueHandlerFactory = new QueueMessageHandlerFactory();
//        queueHandlerFactory.setAmazonSqs(amazonSQSAsync);
//
//        queueHandlerFactory.setArgumentResolvers(
//                Collections.singletonList(new PayloadMethodArgumentResolver(jackson2MessageConverter(mapper))));
//        return queueHandlerFactory;
//    }
//
//    private MessageConverter jackson2MessageConverter(final ObjectMapper mapper) {
//        final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//
//        // set strict content type match to false to enable the listener to handle AWS events
//        converter.setStrictContentTypeMatch(false);
//        converter.setObjectMapper(mapper);
//        return converter;
//    }

    public static void main(String[] args) {

        SpringApplication.run(JmsVirtualizationApplication.class, args);
    }

}
