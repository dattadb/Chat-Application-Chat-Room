package ChatApplication.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ChatApplication.Model.User;

@Repository
public interface UserRepository   extends JpaRepository<User, Long>{

	
	
    @Query(value = "SELECT * FROM user where mobile_no = :mobile_no", nativeQuery = true)
	Optional<User> userGetByMobileNo(String mobile_no);

}
