package sg.edu.iss.team5.unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.team5.model.Student;
import sg.edu.iss.team5.repositories.StudentRepo;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class Team5DataUnitTests {
	@Autowired
	private StudentRepo sRepo;

	  @Test
	@Order(1)
		public void CreateStudentTest() {
			Student student = new Student("S12345", "Leon", "leon@caps.edu.sg");
			sRepo.save(student);
			Student result = sRepo.findById("S12345").get();
			//verify user object created
			assertNotNull(result.getUser());
			//verify attributes of created object is correct
			assertEquals(result.getName(), student.getName());
		}
	  
//	  @Test
//	  @Order(2)
//	  void verifyBootstrappingByPersistingAnAddress() {
//	   Address a = new Address("Heng Mui Keng Terrace", "Singapore", "Singapore");
//	    Assertions.assertNull(a.getAddressId());
//	    em.persist(a);
//	    Assertions.assertNotNull(a.getAddressId());
//	  }
//	  
//	  @Test
//	  @Order(3)
//	  void verifyRepositoryByPersistingAnAddress() {
//	   Address a = new Address("Bouna Vista", "Singapore", "Singapore");
//	    Assertions.assertNull(a.getAddressId());
//	    repository.save(a);
//	    Assertions.assertNotNull(a.getAddressId());
//	  }
	  
	  
}
