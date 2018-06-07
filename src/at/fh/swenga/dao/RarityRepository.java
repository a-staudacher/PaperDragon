package at.fh.swenga.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.Rarity;


@Repository
@Transactional
public interface RarityRepository extends JpaRepository<Rarity, Integer> {
	
}
