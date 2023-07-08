package guru.springframework.spring6restmvc.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false )
    private UUID id;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    private String description;
@Builder.Default
    @ManyToMany
    @JoinTable(name = "beer_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "beer_id"))
    private Set<Beer> beers = new HashSet<>();

//Sure! Imagine you have two things: categories of beer and different types of beer.
// Now, sometimes a category of beer can have many different types of beer,
// and a type of beer can belong to multiple categories.
// So, we need a way to connect these two things together.
//
//In the code snippet, there is a special way to connect the categories and
// types of beer using a join table.
// Think of the join table as a special table that
// helps us establish the connection between the categories and types of beer.
//
//The @JoinTable annotation is like a label that tells our program to create this join table.
// It says that the join table should be named "beer_category". This name helps us identify it.
//
//The joinColumns = @JoinColumn(name = "category_id") part of the code
// tells us that in the join table, there will be a column called "category_id"
// that will be used to keep track of the category of each beer.
// This column acts like a special code that links each type of beer to its respective category.
//
//Similarly, the inverseJoinColumns = @JoinColumn(name = "beer_id") part of the code
// tells us that there will be another column called "beer_id" in the join table.
// This column helps us keep track of the specific type of beer in each category.
// It's like a special code that identifies each type of beer.
//
//Finally, the private Set<Beer> beers; line is where we actually create a
// collection of different types of beer called "beers" inside the category.
// This collection holds all the different types of beer that belong to that particular category.
//
//So, this code helps us connect the categories of beer with the types of beer using a
// special join table. It allows us to easily find out which types of beer belong to each category.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        if (!super.equals(o)) return false;

        Category category = (Category) o;

        return getDescription() != null ? getDescription().equals(category.getDescription()) : category.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
