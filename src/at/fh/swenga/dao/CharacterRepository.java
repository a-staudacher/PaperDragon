package at.fh.swenga.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.Character;


@Repository
@Transactional
public interface CharacterRepository extends JpaRepository<Character, Integer> {
	
	Character findByUserUserName(String userName);
}
