package org.perscholas;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.perscholas.dao.*;
import org.perscholas.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@Transactional
public class AppStartupRunner implements CommandLineRunner {
    IUserRepo userRepo;
    IVendorRepo vendorRepo;
    IAuthGroupRepo authGroupRepo;
    IImagesRepo imagesRepo;
    IOrderRepo orderRepo;
    IProductRepo productRepo;

    @Autowired
    public AppStartupRunner(IUserRepo userRepo, IVendorRepo vendorRepo,
                            IAuthGroupRepo authGroupRepo,
                            IImagesRepo imagesRepo, IOrderRepo orderRepo,
                            IProductRepo productRepo){
        this.authGroupRepo = authGroupRepo;
        this.userRepo = userRepo;
        this.vendorRepo = vendorRepo;
        this.imagesRepo = imagesRepo;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;

    }
    String defPass = "password";
    String bPass = "$2y$11$JcGGFLmi46mM4SKUGKSv8.2srUTueRcyxRoHfHSVK/voHjw9kEKv.";



    @Override
    public void run(String... args) throws Exception {

        log.info("****Starting Application****");

        log.info("****Adding Users****");

        User user1 = new User("andrewjdiner@gmail.com",
                "Andrew", "Diner", "4802986038",
                defPass);
        User user2 = new User("david@gmail.com",
                "David", "Diner", "4802986048",
                defPass);
        User user3 = new User("Billy@gmail.com",
                "Billy", "Thorton","7538983043",
                "$2y$11$JcGGFLmi46mM4SKUGKSv8.2srUTueRcyxRoHfHSVK/voHjw9kEKv.");
        user3.setImagePath("defaultprofile.jpg");
        User user4 = new User("randomemail@yahoo.com",
                "Random", "Person", "1234567890",
                defPass);
        user4.setImagePath("webcamup.png");
        User user5 = new User("adiner@asu.edu",
                "Andrew", "Diner", "4802986038",
                defPass);
        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        userRepo.save(user4);
        userRepo.save(user5);

        //$2y$11$JcGGFLmi46mM4SKUGKSv8.2srUTueRcyxRoHfHSVK/voHjw9kEKv.
        log.info("****Users Added****");


        log.info("****Adding Vendors****");
        Vendor vendor1 = new Vendor("gary@Zoofood.com",
                "Gary", "Diner", "7608352012",
                defPass);

        Vendor vendor2 = new Vendor("nancy@gmail.com",
                "Nancy", "Diner", "8385234021",
                defPass);
        Vendor vendor3 = new Vendor("jimminy@cricket.com",
                "Jimminy", "Cricket", "1234567890",
                defPass);
        Vendor vendor4 = new Vendor("alice@wonderland.com",
                "Alice", "Wonderland", "1234567890",
                defPass);
        vendorRepo.save(vendor1).setImagePath("flamingo.jpg");
        vendorRepo.save(vendor2).setImagePath("nancy.jpg");
        vendorRepo.save(vendor3);
        vendorRepo.save(vendor4);

        log.info("****Vendors Added****");


        log.info("****Adding Authorization Groups****");
        authGroupRepo.save(new AuthGroup("andrewjdiner@gmail.com", "ROLE_ADMIN"));
        authGroupRepo.save(new AuthGroup("andrewjdiner@gmail.com", "ROLE_USER"));
        authGroupRepo.save(new AuthGroup("andrewjdiner@gmail.com", "WRITE"));

        authGroupRepo.save(new AuthGroup("gary@Zoofood@.com", "ROLE_ADMIN"));
        authGroupRepo.save(new AuthGroup("gary@Zoofood@.com", "ROLE_USER"));

        log.info("****Authorization Groups Added****");

        log.info("****Adding Products****");

        Product product1 = new Product("Blue Socks", "Light fabric, sewn in France", 50, 39, 15.99);
        product1.setImagePath("navy-blue-socks-daft-punk.jpg");
        Product product2 = new Product("Basic White Socks", "Found in the Target Clearance Aisle", 100, 88, 2.99);
        Product product3 = new Product("Christmas Socks", "Comfortable red & green socks", 64, 32, 6.99);
        product3.setImagePath("christmas.jpg");

        product1.setVendor(vendor2);
        product2.setVendor(vendor1);
        product3.setVendor(vendor2);



        log.info("****Finished Adding Products****");
//
        productRepo.save(product1);
        productRepo.save(product2);
        productRepo.save(product3);

        log.warn("Made it past product Save");


        Orders order1 = new Orders();
        Orders order2 = new Orders();
        Orders order3 = new Orders();
        Orders order4 = new Orders();
        Orders order5 = new Orders();

        order1.getProductList().add(product1);
        order1.getProductList().add(product3);
        order2.getProductList().add(product2);
        order3.getProductList().add(product2);
        order3.getProductList().add(product2);
        order3.getProductList().add(product2);
        order4.getProductList().add(product3);
        order4.getProductList().add(product3);
        order4.getProductList().add(product3);
        order4.getProductList().add(product3);
        order5.getProductList().add(product1);
        order1.setCustomer(user1);
        order2.setCustomer(user2);
        order3.setCustomer(user5);
        order4.setCustomer(user1);
        order5.setCustomer(user4);


        orderRepo.save(order1);
        orderRepo.save(order2);
        orderRepo.save(order3);
        orderRepo.save(order4);
        orderRepo.save(order5);
        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        userRepo.save(user4);
        userRepo.save(user5);



        userRepo.save(user5);
        userRepo.save(user3);

        vendorRepo.save(vendor1);
        vendorRepo.save(vendor2);



    }

    @PostConstruct
    public void constructed(){
        log.warn("StartupRunner Constructed!");
    }
}
