package partc.dao;

import org.hibernate.*;
import org.springframework.stereotype.Repository;
import partc.entity.Account;
import partc.entity.TransactionRecord;

@Repository
public class AccountDAO {

    private final SessionFactory sessionFactory;

    public AccountDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Account getAccount(int id) {
        return sessionFactory.getCurrentSession().get(Account.class, id);
    }

    public void updateAccount(Account acc) {
        sessionFactory.getCurrentSession().update(acc);
    }

    public void saveTransaction(TransactionRecord record) {
        sessionFactory.getCurrentSession().save(record);
    }
}
