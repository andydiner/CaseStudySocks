package org.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//database
@Entity
//springboot
//@Component
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    Integer productid;
    @NonNull
    @Column(name = "name")
    String name;
    @NonNull
    @Column(name = "description")
    String description;
    @NonNull
    @Column(name = "totalQuantity")
    Integer totalQuantity;
    @NonNull
    @Column(name = "quantityInStock")
    Integer quantityInStock;
    @NonNull
    @Column(name = "price")
    Double price;
    @NonNull
    @Column(name = "imagePath")
    String imagePath = "defaultsocks.jpg";
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "productList")
    List<Orders> orderList;


}
