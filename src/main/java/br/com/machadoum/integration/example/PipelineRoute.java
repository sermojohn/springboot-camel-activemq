package br.com.machadoum.integration.example;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.language.SimpleExpression;
import org.springframework.stereotype.Component;

@Component
public class PipelineRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {


		from("timer:foo?period=1000")
			.setBody(new SimpleExpression("${exchangeId}"))
			.log("submitting new message [${body}]")
			.to("jms:test1");

		from("timer:fooo?period=2000")
			.pollEnrich("jms:test1")
				.log("forwarding message [${body}]")
				.to("jms:test2");

		from("timer:fooo?period=5000")
			.pollEnrich("jms:test2")
			.log("cleaning up message [${body}]");

	}
}