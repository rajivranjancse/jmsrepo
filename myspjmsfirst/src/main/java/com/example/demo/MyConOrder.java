package com.example.demo;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class MyConOrder {

	@Autowired
	private RabbitTemplate template;
	
	@PostMapping("/{strRest}")
	public String bookOrder(@RequestBody MyOrder order,@PathVariable String strRest) 
	{
		order.setOrderId(UUID.randomUUID().toString());
		OrderStatus orderOb= new OrderStatus();
		orderOb.setStrOrderInfo("pizza");
		orderOb.setStrStatus("processing..");
		template.convertAndSend(MessageCon.EXCHANGE,MessageCon.ROUTINGKEY, orderOb);
		return "Success..";
	}
}
