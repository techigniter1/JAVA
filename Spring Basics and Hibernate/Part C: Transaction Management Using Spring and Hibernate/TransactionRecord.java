package partc.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TransactionRecord")
public class TransactionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int from_acc;
    private int to_acc;
    private double amount;
    private String status;

    public TransactionRecord() {}

    public TransactionRecord(int from, int to, double amount, String status) {
        this.from_acc = from;
        this.to_acc = to;
        this.amount = amount;
        this.status = status;
    }
}
