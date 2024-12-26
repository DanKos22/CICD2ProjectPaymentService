package ie.atu.paymentservice;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cards")
public class Card {
    @Id
    private String id;
    @NotBlank(message = "Please enter the card number")
    private String cardNum;
    @NotBlank(message = "Cardtype cannot be blank")
    private String cardType;
    @NotNull(message = "Balance cannot be empty")
    private Double balance;

}
