package partc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import partc.dao.AccountDAO;
import partc.entity.Account;
import partc.entity.TransactionRecord;

@Service
public class BankService {
    private final AccountDAO dao;

    public BankService(AccountDAO dao) {
        this.dao = dao;
    }

    @Transactional
    public void transferMoney(int fromAcc, int toAcc, double amount) {
        Account sender = dao.getAccount(fromAcc);
        Account receiver = dao.getAccount(toAcc);

        if (sender.getBalance() < amount)
            throw new RuntimeException("Insufficient balance");

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        dao.updateAccount(sender);
        dao.updateAccount(receiver);

        dao.saveTransaction(new TransactionRecord(fromAcc, toAcc, amount, "SUCCESS"));
        System.out.println("Transaction completed successfully!");
    }
}
