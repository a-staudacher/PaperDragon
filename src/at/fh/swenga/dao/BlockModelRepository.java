package at.fh.swenga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.BlockModel;


@Repository
@Transactional
public interface BlockModelRepository extends JpaRepository<BlockModel, Integer> {
	List<BlockModel> findByUserUserName(String userName);
	
	BlockModel findByUserUserNameAndBlockedUserUserName(String user, String blockedUser);
}
