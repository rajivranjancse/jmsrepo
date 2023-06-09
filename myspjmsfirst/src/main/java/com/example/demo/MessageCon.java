package com.example.demo;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageCon {
        public static final String QUEUE="java tech";
        public static final String EXCHANGE="My exchange";
     public static final String ROUTINGKEY="javarout";
     
     @Bean
	public Queue queue()
	{
		return new Queue(QUEUE);
	}
	
     @Bean
	public TopicExchange exchange()
	{
		return new TopicExchange(EXCHANGE);
	}
     
     @Bean
     public Binding binding(Queue queue,TopicExchange exchange)
     {
    	 return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY);
     }
	@Bean
     public MessageConverter convertor()
     {
    	return (MessageConverter) new Jackson2JsonMessageConverter();
     }
	
	public AmqpTemplate template(ConnectionFactory connectionFactory)
	{
		RabbitTemplate rabbitTemplate= new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(convertor());
		return rabbitTemplate;
	}
}