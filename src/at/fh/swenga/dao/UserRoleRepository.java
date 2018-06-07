package at.fh.swenga.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.UserRole;


@Repository
@Transactional
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	
	@Query("select ur from UserRole ur where ur.role= :role")
	public UserRole findUserRole(@Param("role") String role);
}
