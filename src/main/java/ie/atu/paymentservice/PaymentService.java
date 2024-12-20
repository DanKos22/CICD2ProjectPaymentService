package ie.atu.paymentservice;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Optional<Transaction> getTransactionById(String id){
        return paymentRepository.findById(id);
    }

    public List<Transaction>createTransaction(Transaction transaction){
        paymentRepository.save(transaction);
        return paymentRepository.findAll();
    }

    public List<Transaction>updateTransaction(String id, Transaction transaction){
        if(paymentRepository.existsById(id)){
            transaction.setId(id);
            paymentRepository.save(transaction);
        }
        return paymentRepository.findAll();
    }

    public List<Transaction>deleteTransaction(String id){
        if(paymentRepository.existsById(id)){
            paymentRepository.deleteById(id);
        }
        return paymentRepository.findAll();
    }
}
