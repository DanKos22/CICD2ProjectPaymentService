package ie.atu.paymentservice;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardTransactionService {

    private final CardRepository cardRepository;
    private final PaymentRepository paymentRepository;

    List<Card>cards = new ArrayList<>();

    public CardTransactionService(CardRepository cardRepository, PaymentRepository paymentRepository) {
        this.cardRepository = cardRepository;
        this.paymentRepository = paymentRepository;
    }

    /*public String<CardTransactionRequest> createCardWithTransactions(CardTransactionRequest cardTransactionRequest){

    }*/
}
