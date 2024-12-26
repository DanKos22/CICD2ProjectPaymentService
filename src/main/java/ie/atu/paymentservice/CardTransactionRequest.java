package ie.atu.paymentservice;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardTransactionRequest {
    @NotNull(message = "Card cannot be blank")
    @Valid
    private Card card;
    @NotEmpty(message = "List cannot have any empty sections")
    @Valid
    private List<Transaction> transactions;
}
