package ie.atu.paymentservice;

import jakarta.validation.constraints.NotBlank;
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
    private String id;

    @NotBlank(message = "Please enter the name for the account")
    private String accountName;
    @NotBlank(message = "Transaction cannot be blank")
    private String transactionType;
    @NotNull(message = "Amount cannot be null")
    private Double amount;
    @NotBlank(message = "Please enter the date of the transaction")
    private String paymentDate;

    private String cardId; // reference to the associated card


}
