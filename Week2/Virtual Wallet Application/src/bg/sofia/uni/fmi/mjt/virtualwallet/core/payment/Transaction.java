package bg.sofia.uni.fmi.mjt.virtualwallet.core.payment;

import java.time.LocalDateTime;

public class Transaction {

    private String name;
    private LocalDateTime dateTime;
    private PaymentInfo paymentInfo;

    public Transaction(String name, LocalDateTime dateTime, PaymentInfo paymentInfo) {
        this.name = name;
        this.dateTime = dateTime;
        this.paymentInfo = paymentInfo;
    }

}
