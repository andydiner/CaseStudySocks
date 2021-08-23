package org.perscholas;

import lombok.extern.java.Log;
import org.perscholas.dao.IAuthGroupRepo;
import org.perscholas.dao.ICustomerRepo;
import org.perscholas.dao.IImagesRepo;
import org.perscholas.dao.IVendorRepo;
import org.perscholas.models.AuthGroup;
import org.perscholas.models.Customer;
import org.perscholas.models.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@Log
@Transactional
public class AppStartupRunner implements CommandLineRunner {
    ICustomerRepo customerRepo;
    IVendorRepo vendorRepo;
    IAuthGroupRepo authGroupRepo;
    IImagesRepo imagesRepo;

    @Autowired
    public AppStartupRunner(ICustomerRepo customerRepo, IVendorRepo vendorRepo,
                            IAuthGroupRepo authGroupRepo){
        this.authGroupRepo = authGroupRepo;
        this.customerRepo = customerRepo;
        this.vendorRepo = vendorRepo;
    }


    @Override
    public void run(String... args) throws Exception {

        log.info("****Starting Application****");
//    String emailAddress;
//    String firstName;
//    String lastName;
//    String phoneNumber;
//    String password;
//    String imagePath;
        log.info("****Adding Customers****");

        customerRepo.save(new Customer("andrewjdiner@gmail.com",
                "Andrew", "Diner", "4802986038",
                "$2y$11$JcGGFLmi46mM4SKUGKSv8.2srUTueRcyxRoHfHSy" +
                        "VK/voHjw9kEKv."));
        customerRepo.save(new Customer("adiner@asu.edu",
                "Andrew", "Diner","4802986038",
                "$2y$11$JcGGFLmi46mM4SKUGKSv8.2srUTueRcyxRoHfHSVK/voHjw9kEKv."));
        customerRepo.save(new Customer("david@gmail.com",
                "David", "Diner", "4802986048",
                "$2y$11$JcGGFLmi46mM4SKUGKSv8.2srUTueRcyxRoHfHSVK/voHjw9kEKv."));
        customerRepo.save(new Customer("Billy@gmail.com",
                "Billy", "Thorton","7538983043",
                "$2y$11$JcGGFLmi46mM4SKUGKSv8.2srUTueRcyxRoHfHSVK/voHjw9kEKv."));
        customerRepo.save(new Customer("randomemail@yahoo.com",
                "Random", "Person", "1234567890",
                "$2y$11$JcGGFLmi46mM4SKUGKSv8.2srUTueRcyxRoHfHSVK/voHjw9kEKv."));
        log.info("****Customers Added****");


        //    String emailAddress;
        //    String firstName;
        //    String lastName;
        //    String phoneNumber;
        //    String password;
        //    String imagePath;
        log.info("****Adding Vendors****");

        vendorRepo.save(new Vendor("gary@Zoofood.com",
                "Gary", "Diner", "7608352012",
                "$2y$11$JcGGFLmi46mM4SKUGKSv8.2srUTueRcyxRoHfHSVK/voHjw9kEKv."));
        vendorRepo.save(new Vendor("nancy@gmail.com",
                "Nancy", "Diner", "8385234021",
                "$2y$11$JcGGFLmi46mM4SKUGKSv8.2srUTueRcyxRoHfHSVK/voHjw9kEKv."));
        vendorRepo.save(new Vendor("jimminy@cricket.com",
                "Jimminy", "Cricket", "1234567890",
                "$2y$11$JcGGFLmi46mM4SKUGKSv8.2srUTueRcyxRoHfHSVK/voHjw9kEKv."));
        vendorRepo.save(new Vendor("alice@wonderland.com",
                "Alice", "Wonderland", "1234567890",
                "$2y$11$JcGGFLmi46mM4SKUGKSv8.2srUTueRcyxRoHfHSVK/voHjw9kEKv."));
        log.info("****Vendors Added****");


        log.info("****Adding Authorization Groups****");
        authGroupRepo.save(new AuthGroup("andrewjdiner@gmail.com", "ROLE_ADMIN"));
        authGroupRepo.save(new AuthGroup("andrewjdiner@gmail.com", "ROLE_USER"));
        authGroupRepo.save(new AuthGroup("andrewjdiner@gmail.com", "WRITE"));

        authGroupRepo.save(new AuthGroup("gary@Zoofood@.com", "ROLE_ADMIN"));
        authGroupRepo.save(new AuthGroup("gary@Zoofood@.com", "ROLE_USER"));

        log.info("****Authorization Groups Added****");


    }

    @PostConstruct
    public void constructed(){
        log.warning("StartupRunner Constructed!");
    }
}
