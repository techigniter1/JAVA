package partc.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    private int acc_no;

    private String holder_name;
    private double balance;

    // Getters and setters
    public int getAcc_no() { return acc_no; }
    public void setAcc_no(int acc_no) { this.acc_no = acc_no; }

    public String getHolder_name() { return holder_name; }
    public void setHolder_name(String holder_name) { this.holder_name = holder_name; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
