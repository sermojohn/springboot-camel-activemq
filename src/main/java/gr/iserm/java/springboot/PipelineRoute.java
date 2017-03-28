package gr.iserm.java.springboot;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.language.SimpleExpression;
import org.springframework.stereotype.Component;

@Component
public class PipelineRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

//		from("timer:foo?period=1000")
		from("direct:input")
			.setBody(new SimpleExpression("${exchangeId}"))
			.to("activemq:test.in");


	}
}