package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//database
@Entity
//springboot
@Component
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)

public class User implements Serializable {
    static final long serialVersionUID = 6381462249347345007L;


    @Id
    @NonNull
    @Column(unique = true) @NotBlank
    String emailAddress;
    @NonNull @NotBlank
    String firstName;
    @NonNull @NotBlank
    String lastName;
    @NonNull @NotBlank
    String phoneNumber;
    @NonNull @NotBlank
    String password;
    String role;
    String imagePath;

    //@ManyToMany
   // List<Order> orders;
}
