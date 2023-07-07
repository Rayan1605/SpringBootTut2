package guru.springframework.spring6restmvc.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerOrder {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID", //This is the name of the generator
            strategy = "org.hibernate.id.UUIDGenerator" //This is the strategy of the generator
    )
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID id;
    @Version
private Long version;
    @CreationTimestamp
    @Column(updatable = false)// so you can't change the created date
    private Timestamp created_date;

    @UpdateTimestamp// so you can't change the last modified date and hibernate will take care of
    // it
    private Timestamp lastModifiedDate;

    public boolean isNew() {
        return this.id == null;
    }
    private String customerRef;
    @ManyToOne // many orders to one customer
    // Then go to the Customer class and add the @OneToMany(mappedBy = "customer") annotation
    //    private Set<BeerOrder> beerOrders; Since there can be many orders to one customer
   private Customer customer;
@OneToMany(mappedBy = "beerOrder") // one order to many order lines
    private Set<BeerOrderLine> beerOrderLineSet;



}
