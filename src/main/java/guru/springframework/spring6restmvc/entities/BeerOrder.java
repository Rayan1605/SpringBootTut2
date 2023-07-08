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
        this.setBeerOrderShipment(beerOrderShipment);
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

    public void setBeerOrderShipment(BeerOrderShipment beerOrderShipment) {
        this.beerOrderShipment = beerOrderShipment;
        beerOrderShipment.setBeerOrder(this);
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

    @OneToOne(cascade = CascadeType.PERSIST)
    //cascade refers to the behavior that occurs when a certain operation is performed
    // on a parent entity, and how it affects the associated child entities.
    //
    //Imagine you have two entities: a parent entity and a child entity.
    // The parent entity has a relationship with the child entity,
    // such as a one-to-one, one-to-many, or many-to-many relationship.
    //
    //Cascade operations define what should happen to the child entities when certain
    // operations are performed on the parent entity. These operations typically include:
    //
    //Persist (or Save): When the parent entity is saved to the database, cascade can ensure that the associated child entities
    // are also saved automatically.
    //
    //Merge: When changes are made to the parent entity and the changes are merged into the database, cascade can propagate
    // those changes to the associated child entities as well.
    //
    //Remove (or Delete): When the parent entity is deleted from the database, cascade can ensure that the associated
    // child entities are also deleted.
    //
    //Refresh: When the parent entity is refreshed from the database, cascade can refresh the associated child entities as well.
    //
    //Detach: When the parent entity is detached from the persistence context (usually when it's no longer actively managed),
    // cascade can detach the associated child entities as well.
    private BeerOrderShipment beerOrderShipment;


}
