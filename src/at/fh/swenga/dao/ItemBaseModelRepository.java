package at.fh.swenga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.ItemBaseModel;


@Repository
@Transactional
public interface ItemBaseModelRepository extends JpaRepository<ItemBaseModel, Integer> {
	List<ItemBaseModel> findByNameContainingAllIgnoreCase(String name);
}
