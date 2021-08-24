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

        userRepo.save(new User("andrewjdiner@gmail.com",
                "Andrew", "Diner", "4802986038",
                defPass));
        userRepo.save(new User("adiner@asu.edu",
                "Andrew", "Diner","4802986038",
                defPass));
        userRepo.save(new User("david@gmail.com",
                "David", "Diner", "4802986048",
                defPass));
        userRepo.save(new User("Billy@gmail.com",
                "Billy", "Thorton","7538983043",
                "$2y$11$JcGGFLmi46mM4SKUGKSv8.2srUTueRcyxRoHfHSVK/voHjw9kEKv.")).setImagePath("defaultprofile.jpg");
        userRepo.save(new User("randomemail@yahoo.com",
                "Random", "Person", "1234567890",
                defPass)).setImagePath("webcamup.png");
        //$2y$11$JcGGFLmi46mM4SKUGKSv8.2srUTueRcyxRoHfHSVK/voHjw9kEKv.
        log.info("****Users Added****");


        log.info("****Adding Vendors****");

        vendorRepo.save(new Vendor("gary@Zoofood.com",
                "Gary", "Diner", "7608352012",
                defPass));
        vendorRepo.save(new Vendor("nancy@gmail.com",
                "Nancy", "Diner", "8385234021",
                defPass));
        vendorRepo.save(new Vendor("jimminy@cricket.com",
                "Jimminy", "Cricket", "1234567890",
                defPass));
        vendorRepo.save(new Vendor("alice@wonderland.com",
                "Alice", "Wonderland", "1234567890",
                defPass));
        log.info("****Vendors Added****");


        log.info("****Adding Authorization Groups****");
        authGroupRepo.save(new AuthGroup("andrewjdiner@gmail.com", "ROLE_ADMIN"));
        authGroupRepo.save(new AuthGroup("andrewjdiner@gmail.com", "ROLE_USER"));
        authGroupRepo.save(new AuthGroup("andrewjdiner@gmail.com", "WRITE"));

        authGroupRepo.save(new AuthGroup("gary@Zoofood@.com", "ROLE_ADMIN"));
        authGroupRepo.save(new AuthGroup("gary@Zoofood@.com", "ROLE_USER"));

        log.info("****Authorization Groups Added****");

        log.info("****Adding Products****");

        Product frog = new Product("Frog legs", "Bull Frog legs from France", 50, 39, 15.99);
        frog.setImagePath("navy-blue-socks-daft-punk.jpg");
        Product eggs = new Product("Brown Eggs", "Bull Frog eggs laid by Tommy", 100, 88, 2.99);

        log.info("****Finished Adding Products****");

        productRepo.save(frog);
        productRepo.save(eggs);

        Orders myOrder =  new Orders("adiner@asu.edu", "gary@Zoofood.com");
//
//        List<Product> frogEgg = new ArrayList<>();
//        frogEgg.add(frog);
//        frogEgg.add(eggs);
//        log.warn("List of Products to be added: " + frogEgg.toString());
        // myOrder.setProductList(frogEgg);


        orderRepo.save(myOrder);
        myOrder.getProductList().add(frog);

        myOrder.getProductList().add(eggs);


//
//        frog.getOrderList().add(myOrder);
//        eggs.getOrderList().add(myOrder);






    }

    @PostConstruct
    public void constructed(){
        log.warn("StartupRunner Constructed!");
    }
}
