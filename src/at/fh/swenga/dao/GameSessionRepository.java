package at.fh.swenga.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.GameSession;


@Repository
@Transactional
public interface GameSessionRepository extends JpaRepository<GameSession, Integer> {
	GameSession findByName(String name);
}
