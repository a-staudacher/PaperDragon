package at.fh.swenga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.Chat;


@Repository
@Transactional
public interface ChatRepository extends JpaRepository<Chat, Integer> {
	List<Chat> findByGameSessionName(String name);
}
