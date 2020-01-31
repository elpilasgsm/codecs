package org.sfedu.codecs.repository.db;

import org.sfedu.codecs.model.db.ChangesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangesRepository extends JpaRepository<ChangesEntity, Long> {
    public List<ChangesEntity> getByRecordRecordIdOrderByDate(Long parentId);
}
