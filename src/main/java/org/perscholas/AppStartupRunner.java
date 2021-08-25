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
        vendorRepo.save(vendor1);
        vendorRepo.save(vendor2);
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

        Product frog = new Product("Blue Socks", "Light fabric, sewn in France", 50, 39, 15.99);
        frog.setImagePath("navy-blue-socks-daft-punk.jpg");
        Product eggs = new Product("Basic White Socks", "Found in the Target Clearance Aisle", 100, 88, 2.99);

        log.info("****Finished Adding Products****");

        productRepo.save(frog);
        productRepo.save(eggs);

        Orders myOrder =  new Orders("adiner@asu.edu", "gary@Zoofood.com");
        Orders myOrder2 = new Orders("Billy@gmail.com", "nancy@gmail.com");

        Orders myOrder3 = new Orders();

//        List<Product> frogEgg = new ArrayList<>();
//        frogEgg.add(frog);
//        frogEgg.add(eggs);
//        log.warn("List of Products to be added: " + frogEgg.toString());
        // myOrder.setProductList(frogEgg);


        orderRepo.save(myOrder);
        orderRepo.save(myOrder2);

        myOrder.getProductList().add(frog);

        myOrder.getProductList().add(eggs);

//
//        frog.getOrderList().add(myOrder);
//        eggs.getOrderList().add(myOrder);

        user5.getOrdersList().add(myOrder);
        userRepo.save(user5);
        vendor1.getOrdersList().add(myOrder);
        vendor1.getOrdersList().add(myOrder3);
        if(myOrder3.getVendorEmail() == null){
            myOrder3.setVendorEmail(vendor1.getEmailAddress());
        }
        vendorRepo.save(vendor1);




//
//        myOrder.getCustomers().add(user1);
//        myOrder.getCustomers().add(user5);

        log.warn(user5.getEmailAddress() + "'s OrderList Size: " + user5.getOrdersList().toString());
        log.warn("Order at 1: " + String.valueOf(user5.getOrdersList().get(0).getProductList().get(1).getName()));




    }

    @PostConstruct
    public void constructed(){
        log.warn("StartupRunner Constructed!");
    }
}
