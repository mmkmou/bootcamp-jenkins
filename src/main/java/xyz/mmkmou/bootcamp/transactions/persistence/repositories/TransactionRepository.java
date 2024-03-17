package xyz.mmkmou.bootcamp.transactions.persistence.repositories;

import xyz.mmkmou.bootcamp.transactions.persistence.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByFref(Long fref);

    Transaction findFirstByFrefAndAmountAndPhoneNumber(Long fref, Integer amount, String phone);
}
