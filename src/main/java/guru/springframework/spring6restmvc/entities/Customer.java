package guru.springframework.spring6restmvc.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)//Tell hibernate to store UUID as it characterValue which is a string
    //If you don't put this in it will send it as a binary value which is not what we want
    //Hibernate uses a binary representation for storing UUID values by
    // default because the java.util.UUID type in Java is typically mapped to a
    // binary data type in databases.

    //When I used @JdbcTypeCode(SqlTypes.CHAR) in your code,
    // I explicitly instructed Hibernate to store the UUID as a
    // character value (string) instead of the default binary representation.
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;
    private String name;
    @Column(length = 255)
    private String Email;

    @Version
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
