package gr.iserm.java.springboot;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@MockEndpoints
@ContextConfiguration(classes = {Application.class, ApplicationTest.ContextConfig.class})
public class ApplicationTest {

    @Produce
    private ProducerTemplate template;

    @EndpointInject(uri = "mock://activemq:test.out")
    private MockEndpoint activemqOutputMockendpoint;

    @Test
    public void testSubmission() throws InterruptedException {
        activemqOutputMockendpoint.expectedMessageCount(1);
        template.sendBody("direct:input", "test");
        activemqOutputMockendpoint.assertIsSatisfied();
    }

    @Configuration
    public static class ContextConfig {

        @Bean
        public RouteBuilder testRoute() {
            return new RouteBuilder() {
                public void configure() {

                    from("activemq:test.in")
                        .to("activemq:test.out");

                }
            };
        }
    }
}
