//import org.assertj.core.api.Assertions;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.perscholas.CaseRunner;
//import org.perscholas.dao.IUserRepo;
//import org.perscholas.models.User;
//import org.perscholas.services.UserServices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Optional;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
////@ComponentScan
////@EnableJpaRepositories()
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class UserServicesTest {
////
////
//////
//////    @Autowired
//////    private UserServices userServices;
////
////    @MockBean
////    private IUserRepo iUserRepo;
////
////    @Test
////    public void testAddNew(){
////        User user = new User();
////        user.setEmailAddress("andrewjdiner@gmail.com");
////        user.setPassword("password");
////        user.setFirstName("Andrew");
////        user.setLastName("Diner");
////        user.setPhoneNumber("4802986038");
////        User savedUser = iUserRepo.save(user);
////
////        Assertions.assertThat(savedUser).isNotNull();
////        Assertions.assertThat(savedUser.getEmailAddress()).isNotNull();
////
////    }
////
////    @Test
////    public void testAll(){
////        //System.out.println(iUserRepo);
////
////        Iterable<User> users = iUserRepo.findAll();
////        Assertions.assertThat(users).hasSizeGreaterThan(0);
////       // System.out.println(users);
////        for(User user: users){
////            System.out.println(user);
////        }
////    }
////
////    @Test
////    public void testUpdate(){
////        String testEmail = "randomemail@yahoo.com";
////        Optional<User> userOptional = iUserRepo.findById(testEmail);
////        User user = userOptional.get();
////        user.setPassword("updatedpassword");
////        iUserRepo.save(user);
////        User updatedUser = iUserRepo.findById(testEmail).get();
////        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("updatedpassword");
////    }
////
////
////    @Test
////    public void testGet(){
////        String testEmail = "randomemail@yahoo.com";
////        Optional<User> userOptional = iUserRepo.findById(testEmail);
////        Assertions.assertThat(userOptional).isPresent();
////        System.out.println(userOptional.get());
////    }
////
////    @Test
////    public void testDelete(){
////        String testEmail = "randomemail@yahoo.com";
////        iUserRepo.deleteById(testEmail);
////        Optional<User> userOptional = iUserRepo.findById(testEmail);
////        Assertions.assertThat(userOptional).isNotPresent();
////
////    }
//}
