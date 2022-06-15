package sg.edu.iss.team5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.team5.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
