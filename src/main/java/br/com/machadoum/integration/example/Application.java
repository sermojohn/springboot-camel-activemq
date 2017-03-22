package br.com.machadoum.integration.example;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.jms.ConnectionFactory;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
     }

    /*
	@Bean
	public SpringCamelContext camelContext(ApplicationContext applicationContext) throws Exception {
		SpringCamelContext camelContext = new SpringCamelContext(applicationContext);
		camelContext.addRoutes(routeBuilder());
		return camelContext;
	}

	@Bean
	public RouteBuilder routeBuilder() {
		return new PipelineRoute();
	}
	*/

	@Bean
	public ConnectionFactory ConnectionFactory(@Value("${broker.url}") String url) {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(url);
		return activeMQConnectionFactory;
	}



	/*@Configuration
	public static class MyAppConfig {

		@Bean
		CamelContextConfiguration contextConfiguration() {
			return new CamelContextConfiguration() {
				public void beforeApplicationStart(CamelContext context) {
					// your custom configuration goes here
				}

				public void afterApplicationStart(CamelContext camelContext) {

				}
			};
		}
	}*/

}

