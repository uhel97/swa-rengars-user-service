package cz.cvut.fel.oi.swa.rengars.userservice.rest.users.services;

import cz.cvut.fel.oi.swa.rengars.userservice.rest.users.dtos.requests.CreateOrUpdateUserDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    public void send(String str){
        rabbitTemplate.convertAndSend(exchange,routingkey, str);
//        System.out.println("Send msg = " + createOrUpdateUserDTO);
    }

}
