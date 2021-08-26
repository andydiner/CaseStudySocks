import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.perscholas.dao.IVendorRepo;
import org.perscholas.models.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest

public class VendorRepoTest {

//    @Autowired
//    IVendorRepo iVendorRepo;
//
//    @Test
//    public void testRepository()
//    {
//        Vendor vendor = new Vendor();
//        vendor.setFirstName("Lokesh");
//        vendor.setLastName("Gupta");
//        vendor.setEmailAddress("howtodoinjava@gmail.com");
//
//        iVendorRepo.save(vendor);
//
//        Assert.assertNotNull(vendor.getEmailAddress());
//    }
}
