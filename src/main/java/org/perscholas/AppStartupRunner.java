package org.perscholas;

import lombok.extern.java.Log;
import org.perscholas.dao.IAuthGroupRepo;
import org.perscholas.dao.IUserRepo;
import org.perscholas.dao.IImagesRepo;
import org.perscholas.dao.IVendorRepo;
import org.perscholas.models.AuthGroup;
import org.perscholas.models.User;
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
    IUserRepo userRepo;
    IVendorRepo vendorRepo;
    IAuthGroupRepo authGroupRepo;
    IImagesRepo imagesRepo;

    @Autowired
    public AppStartupRunner(IUserRepo userRepo, IVendorRepo vendorRepo,
                            IAuthGroupRepo authGroupRepo){
        this.authGroupRepo = authGroupRepo;
        this.userRepo = userRepo;
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
        log.info("****Adding Users****");

        userRepo.save(new User("andrewjdiner@gmail.com",
                "Andrew", "Diner", "4802986038",
                "password"));
        userRepo.save(new User("adiner@asu.edu",
                "Andrew", "Diner","4802986038",
                "password"));
        userRepo.save(new User("david@gmail.com",
                "David", "Diner", "4802986048",
                "password"));
        userRepo.save(new User("Billy@gmail.com",
                "Billy", "Thorton","7538983043",
                "$2y$11$JcGGFLmi46mM4SKUGKSv8.2srUTueRcyxRoHfHSVK/voHjw9kEKv."));
        userRepo.save(new User("randomemail@yahoo.com",
                "Random", "Person", "1234567890",
                "password"));
        //$2y$11$JcGGFLmi46mM4SKUGKSv8.2srUTueRcyxRoHfHSVK/voHjw9kEKv.
        log.info("****Users Added****");


        //    String emailAddress;
        //    String firstName;
        //    String lastName;
        //    String phoneNumber;
        //    String password;
        //    String imagePath;
        log.info("****Adding Vendors****");

        vendorRepo.save(new Vendor("gary@Zoofood.com",
                "Gary", "Diner", "7608352012",
                "password"));
        vendorRepo.save(new Vendor("nancy@gmail.com",
                "Nancy", "Diner", "8385234021",
                "password"));
        vendorRepo.save(new Vendor("jimminy@cricket.com",
                "Jimminy", "Cricket", "1234567890",
                "password"));
        vendorRepo.save(new Vendor("alice@wonderland.com",
                "Alice", "Wonderland", "1234567890",
                "password"));
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
