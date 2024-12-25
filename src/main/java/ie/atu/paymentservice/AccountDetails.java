package ie.atu.paymentservice;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetails {

    private String accountName;
    private String accountType;
    private Double accountBalance;
}
