package ie.atu.paymentservice;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transactions")

public class Transaction {

    @Id
    private Long id;

    private Long accountId;
    @NotNull(message = "Transaction cannot be blank")
    private String transactionType;
    @NotNull(message = "Amount cannot be null")
    private Double amount;
    @PastOrPresent(message = "Cannot be a future date")
    private LocalDateTime dateTime;

}
