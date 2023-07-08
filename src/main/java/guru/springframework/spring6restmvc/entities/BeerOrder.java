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

@Builder
public class BeerOrder {
    public BeerOrder(UUID id, Long version, Timestamp created_date, Timestamp lastModifiedDate, String customerRef, Customer customer, Set<BeerOrderLine> beerOrderLineSet,
                     BeerOrderShipment beerOrderShipment) {
        this.id = id;
        this.version = version;
        this.created_date = created_date;
        this.lastModifiedDate = lastModifiedDate;
        this.customerRef = customerRef;
        this.beerOrderShipment = beerOrderShipment;
        this.SetCustomer(customer); // so we can ovveride because we are calling the builder method
        // it will use all args constructor so we override to make sure it uses our
        // setCustomer method instead of the constructor
        this.beerOrderLineSet = beerOrderLineSet;
    }

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

    public void SetCustomer(Customer customer){
        this.customer=customer;
        customer.getBeerOrders().add(this); // the "this" is referring to the current beer order
    }
@OneToMany(mappedBy = "beerOrder") // one order to many order lines
    private Set<BeerOrderLine> beerOrderLineSet;

    @OneToOne
    private BeerOrderShipment beerOrderShipment;


}
