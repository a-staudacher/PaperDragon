package at.fh.swenga.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.fh.swenga.model.DocumentModel;


@Repository
@Transactional
public interface DocumentModelRepository extends JpaRepository<DocumentModel, Integer> {
	
}
