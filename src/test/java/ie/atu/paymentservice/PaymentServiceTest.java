package ie.atu.paymentservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateTransaction() {
        // Create a dummy transaction object
        Transaction transaction = new Transaction("1", "Andrew", "Cash Lodgement", 500.0, "07/01/2025", null);
        // Mock the save operation to return the same transaction
        when(paymentRepository.save(transaction)).thenReturn(transaction);
        // Call the service method
        paymentService.createTransaction(transaction);

        // Assertions
        assertEquals("Andrew", transaction.getAccountName());
        assertEquals("Cash Lodgement", transaction.getTransactionType());
        assertEquals(500.0, transaction.getAmount());
    }
}
