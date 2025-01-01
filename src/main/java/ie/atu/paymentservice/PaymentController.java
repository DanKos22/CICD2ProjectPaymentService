package ie.atu.paymentservice;


import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentRepository paymentRepository;
    private final PaymentService paymentService;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    List<Transaction> payments = new ArrayList<>();
    public PaymentController(PaymentRepository paymentRepository, PaymentService paymentService, RabbitTemplate rabbitTemplate) {
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping()
    public List<Transaction> getPayments(){
        return paymentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Transaction> getTransactionById(@PathVariable String id){
        return paymentService.getTransactionById(id);
    }

    @PostMapping
    public ResponseEntity<List<Transaction>>createTransaction(@Valid @RequestBody Transaction transaction){
        payments = paymentService.createTransaction(transaction);
        return ResponseEntity.ok(payments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Transaction>>updateTransaction(@PathVariable String id, @Valid @RequestBody Transaction newTransaction){
        payments = paymentService.updateTransaction(id, newTransaction);
        return ResponseEntity.ok(payments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Transaction>>deleteTransaction(@PathVariable String id){
        payments = paymentService.deleteTransaction(id);
        return ResponseEntity.ok(payments);
    }

    /*@PostMapping("/paymentsNotify")
    public String paymentsNotification(@RequestBody Payment payment){
        String message = String.format("The following payment has been made from account: %s, transaction: %s, amount: %.2f",
                payment.getName(), payment.getTransactionType(), payment.getAmount());
        rabbitTemplate.convertAndSend("paymentQueue", message);
        return message;
    }*/

    @PostMapping("/balanceNotify")
    public String sendBalance(@RequestBody AccountDetails accountDetails){
        String balanceMessage = String.format("Details for account: %s, account type: %s, balance: %.2f",
                accountDetails.getAccountName(), accountDetails.getAccountType(), accountDetails.getAccountBalance());
        return balanceMessage;
    }

    @PostMapping("/something")
    public String paymentsNotification(@RequestBody Payment payment){
        String message = String.format("The following payment has been made from account: %s, transaction: %s, amount: %.2f",
                payment.getName(), payment.getTransactionType(), payment.getAmount());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE,"paymentQueue", message);
        return message;
    }

}
