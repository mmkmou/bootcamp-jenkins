package xyz.mmkmou.bootcamp.transactions.persistence.models;


import lombok.*;
import xyz.mmkmou.bootcamp.transactions.api.models.transactions.SourceEnum;
import xyz.mmkmou.bootcamp.transactions.api.models.transactions.TransactionTypeEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "transactions",
        schema = "txns",
        uniqueConstraints={
                @UniqueConstraint(columnNames = {"ref", "fromPhoneNumber"})
        }
)
public class Transaction {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Version
    private Long version;

    @Column(length = 20,nullable = false, updatable = false, columnDefinition = "varchar")
    private String ref;

    @Column(nullable = false, updatable = false, columnDefinition = "bigint")
    private Long fref;

    @Column(columnDefinition = "integer", nullable = false, updatable = false)
    private int amount;

    @Column(columnDefinition = "numeric", nullable = false, updatable = false)
    private Double balance;

    @Column(updatable = false)
    private Timestamp paymentDate;

    @Column(nullable = false, updatable = false, columnDefinition = "varchar")
    private String phoneNumber;

    @Column(nullable = false, updatable = false)
    private TransactionTypeEnum transactionType;

    @Column(nullable = false, columnDefinition = "boolean")
    private boolean isPrinted;

    @Column(nullable = false, updatable = false, columnDefinition = "varchar")
    private String fromPhoneNumber;

//    @Column(columnDefinition = "integer default 0")
    private SourceEnum transactionSource;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp updatedDate;
}
