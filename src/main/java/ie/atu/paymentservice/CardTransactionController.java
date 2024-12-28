package ie.atu.paymentservice;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CardTransactionController {
    private final CardRepository cardRepository;
    private final PaymentRepository paymentRepository;

    List<Card>cards = new ArrayList<>();

    public CardTransactionController(CardRepository cardRepository, PaymentRepository paymentRepository) {
        this.cardRepository = cardRepository;
        this.paymentRepository = paymentRepository;
    }

    @PostMapping("/card-with-transactions")
    public ResponseEntity<String> createCardWithTransactions(@Valid @RequestBody CardTransactionRequest cardTransactionRequest){

        //Save the card and get the generated card ID
        Card card = cardTransactionRequest.getCard();
        Card savedCard = cardRepository.save(card);
        String cardId = savedCard.getId();

        //Save the transactions and link them to the card
        List<Transaction> transactions = cardTransactionRequest.getTransactions();
        for(Transaction transaction : transactions){
            transaction.setCardId(card.getId());
            paymentRepository.save(transaction);
        }
        return ResponseEntity.ok("Card and transactions created successfully");
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<String> updateCardWithOrders(@PathVariable String cardId, @RequestBody CardTransactionRequest cardTransactionRequest){
        if(cardRepository.existsById(cardId)) {
            Card card = cardTransactionRequest.getCard();
            card.setId(cardId);
            cardRepository.save(card);
        }
        List<Transaction> transactions = cardTransactionRequest.getTransactions();

        for(Transaction transaction : transactions) {
            if(paymentRepository.existsById(cardId)) {
                transaction.setId(cardId);
                paymentRepository.save(transaction);
            }
        }
        return ResponseEntity.ok("Card and transactions updated successfully");
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<String> deleteCardWithTransactions(@PathVariable String cardId){

        List<Transaction> transactions = paymentRepository.findByCardId(cardId);
        if(!transactions.isEmpty()){
            paymentRepository.deleteAll(transactions);
        }

        Optional<Card> card = cardRepository.findById(cardId);
        if(card.isPresent()){
            cardRepository.deleteById(cardId);
        }
        return ResponseEntity.ok("Card and transactions deleted successfully");
    }
}
