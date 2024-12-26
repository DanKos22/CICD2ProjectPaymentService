package ie.atu.paymentservice;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentRepository extends MongoRepository<Transaction, String> {
    List<Transaction>findByCardId(String cardId);
}
