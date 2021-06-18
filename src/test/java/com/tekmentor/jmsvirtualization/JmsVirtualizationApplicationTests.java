package com.tekmentor.jmsvirtualization;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

//@SpringBootTest
class JmsVirtualizationApplicationTests {

    //https://sqs.eu-west-1.amazonaws.com/844154758539/DemoQueue
    @Test
    void testMessage() throws InterruptedException {
        int i = 0;
        while (i < 5){
            System.out.println("Going to sleep again ");
            given()
                    .log().all()
                    .header("Content-type", "application/json")
                    .body("{\"message\": \"Shobhit1\"}")
                    .when()
                    .post("http://localhost:9800/sqs")
                    .then()
                    .statusCode(201);


            Thread.sleep(5000);
            i++;
        }
    }
}
