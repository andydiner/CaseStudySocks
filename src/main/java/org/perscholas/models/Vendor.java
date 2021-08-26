package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//database
@Entity
//springboot
@Component
public class Vendor implements Serializable {
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
    String imagePath ="defaultprofile.jpg";
    double moneyEarned;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "Order_Vendor",
//            joinColumns = @JoinColumn(name = "emailAddress", referencedColumnName = "emailAddress", nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "orderid", referencedColumnName = "orderid", nullable = false))
//    List<Orders> ordersList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vendor")
    List<Product> productList = new ArrayList<>();

}
