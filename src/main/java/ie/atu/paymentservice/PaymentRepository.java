package ie.atu.paymentservice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Transaction, String> {
}
