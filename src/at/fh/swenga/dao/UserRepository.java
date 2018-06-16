package at.fh.swenga.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.DocumentModel;
import at.fh.swenga.model.User;


@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.userName = :username")
	public User findUser(@Param("username") String username);
}
