package at.fh.swenga.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.BlockModel;


@Repository
@Transactional
public interface BlockModelRepository extends JpaRepository<BlockModel, Integer> {
	
}
