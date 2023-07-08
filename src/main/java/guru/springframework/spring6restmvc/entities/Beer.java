package guru.springframework.spring6restmvc.entities;

import guru.springframework.spring6restmvc.model.BeerStyle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)//Tell hibernate to store UUID as it characterValue
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;
    @Version
    private Integer version;
    @NotNull
    @Size(min = 1, max = 100) // this mean min character 3 and max is 100
    @NotBlank
    private String beerName;
    @NotNull
    private BeerStyle beerStyle;
    @NotNull
    @NotBlank
    private String upc;
    private Integer quantityOnHand;
    @NotNull
    private BigDecimal price;

    @OneToMany(mappedBy = "beer")//this mean one beer to many order lines
    private Set<BeerOrderLine> beerOrderLines;
    @Builder.Default
    @ManyToMany
    @JoinTable(name = "beer_category", joinColumns = @JoinColumn(name = "beer_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category) {
        categories.add(category);
        category.getBeers().add(this);// this refer to the current beer
    }
    public void removeCategory(Category category) {

   this.categories.remove(category);
   category.getBeers().remove(category);
    }


    @CreationTimestamp // Hibernate will set this value when we create a new record automatically
    private LocalDateTime createdDate;
@UpdateTimestamp
    private LocalDateTime updateDate;
}
