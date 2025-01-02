package ie.atu.paymentservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class accountServiceListener {
    @RabbitListener(queues = "balanceQueue")
    public void balanceNotification(String message) {
        System.out.println(message);
    }
}
