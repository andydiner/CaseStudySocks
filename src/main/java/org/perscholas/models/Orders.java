package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.perscholas.services.OrderServices;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//database
//springboot
@Entity
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    Integer orderid;
    @NonNull
    double totalPrice;
    @ManyToOne()
            //name = "orderid", nullable = false, insertable = false, updatable = false
    User customer;

    @NonNull
//    @ManyToMany(mappedBy = "ordersList")
//    //name = "orderid", nullable = false, insertable = false, updatable = false
//    List<Vendor> vendorList;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinTable(name = "Orders_Product",
            joinColumns = @JoinColumn(name = "orderid", referencedColumnName = "orderid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "productid", referencedColumnName = "productid", nullable = false))
            List<Product> productList = new ArrayList<>();


            //, targetEntity = Product.class

//            //
            //



}
